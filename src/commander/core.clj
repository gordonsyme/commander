(ns commander.core
  (:require [clojure.tools.logging :refer (info)]
            [clojure.string :as string]
            [clojure.java.shell :refer (sh)]
            [clojure.pprint :as pp]
            [aleph.http :as http])
  (:gen-class))

(set! *warn-on-reflection* true)

(defn handler
  [req]
  (pp/pprint req)
  (let [command (:uri req)
        args (when-let [query (:query-string req)]
               (string/split query #"="))
        {:keys [exit out err]} (if args
                                  (apply sh command args)
                                  (sh command))]
    {:status (if (zero? exit) 200 400)
     :body (format "out:\n%s\n\nerr:\n%s\n" out err)
     :headers {"content-type" "text/plain"}}))

(defn -main
  [& args]
  (info "starting...")
  (http/start-server handler {:port 2222})
  (loop [i 0]
    (Thread/sleep 1000)
    (if (> i 60)
      (do (info "Still running...")
          (recur 0))
      (recur (inc i)))))
