(ns itgm-service.auth.core
  (:require [itgm-service.utils :refer [json-parse]]
            [itgm-service.utils :refer [encode-base64]]
            [cheshire.core :refer [generate-string]]))

(defn auth []
  (let [client {:client_id (or (System/getenv "SENDPULSE_CLIENT_ID") "client_id")
                :client_secret (or (System/getenv "SENDPULSE_CLIENT_SECRET") "client_secret")
                :grant_type "client_credentials"}
        resp @(org.httpkit.client/request
               {:url "https://api.sendpulse.com/oauth/access_token"
                :headers {"Content-Type" "application/json"}
                :method :post
                :body (generate-string client)})]
    resp))

(defn get-token []
  (->> (auth)
       :body
       json-parse
       :access_token
       (str "Bearer ")))

(defn get-basic-app-auth []
  (let [id (or (System/getenv "AUTH_CLIENT_ID") "service")
        secret (or (System/getenv "AUTH_SECRET") "84hfxckku3213h")]
    (encode-base64 (str id ":" secret))))
