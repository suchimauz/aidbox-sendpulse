(ns igtm-service.action.core
  (:require [igtm-service.utils :refer [json-parse]]
            [igtm-service.auth.core :as auth]
            [cheshire.core :refer [generate-string]]))

(defn get-resource [rt & [id]]
  (let [resp @(org.httpkit.client/request
              {:url (str (or (System/getenv "AIDBOX_URL") "http://localhost:8080")
                         "/" (name rt) "/" (when id id))
               :deadlock-guard? false
               :headers {"Authorization" (str "Basic " (auth/get-basic-app-auth))}})]
    (json-parse (:body resp))))

(defn main [{params :params :as args}]
  (let [user (get-resource :UserProfile (get params "id"))
        community (get-resource :Community (get-in user [:community :id]))
        mailbook {:emails [{:email (:email user)
                            :variables {"Name" (:name user)
                                        "Community" (:name community)
                                        "Specialization" (:specialization user)
                                        "Company" (:company user)
                                        "Experience" (:experience user)}}]}
        resp @(org.httpkit.client/request
               {:url "https://api.sendpulse.com/addressbooks/558797/emails"
                :headers {"Content-Type" "application/json"
                          "Authorization" (auth/get-token)}
                :method :post
                :body (generate-string mailbook)})]
      (println resp)))
