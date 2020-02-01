(ns igtm-service.utils
  (:require [clojure.data.json :as json])
  (:import java.util.Base64))

(defn json-parse [json]
  (json/read-str json :key-fn #(keyword %)))

(defn encode-base64 [to-encode]
  (.encodeToString (Base64/getEncoder) (.getBytes to-encode)))
