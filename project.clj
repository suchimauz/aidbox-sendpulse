(defproject itgm-service "0.1.0-SNAPSHOT"
  :description "Service for create contacs for sendmail"
  :url "http://github.com/suchimauz"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [cheshire "5.9.0"]
                 [compojure "1.1.6"]
                 [ring/ring-json "0.5.0"]
                 [ring/ring-core "1.8.0"]
                 [ring "1.8.0"]
                 [org.clojure/data.json "0.2.7"]
                 [clj-commons/clj-yaml "0.7.0"]
                 [http-kit "2.4.0-alpha3"]]
  :main ^:skip-aot itgm-service.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
