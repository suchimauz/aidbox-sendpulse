(ns itgm-service.core
  (:use
   [clojure.java.io]
   [compojure core response]
   [ring.adapter.jetty :only [run-jetty]]
   [ring.util.response]
   [ring.middleware file file-info stacktrace reload json])
  (:require [compojure.route :as route]
            [org.httpkit.client]
            [itgm-service.action.core :as action]
            [clojure.string :as str])
  (:gen-class))

(defroutes main-routes
  (POST "/" args (action/main args))
  (route/not-found "NOT FOUND"))

(def app
  (-> main-routes
      (wrap-reload '(itgm-service.core))
      (wrap-json-body)
      (wrap-json-params)
      (wrap-file-info)
      (wrap-stacktrace)))

(defn start-server
  []
  (run-jetty #'app {:port 8085 :join? false}))

(defn -main [& args]
  (start-server))
