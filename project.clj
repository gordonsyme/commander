(defproject commander "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :pedantic? :abort
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [aleph "0.4.1"]]

  :main ^:skip-aot commander.core
  :profiles {:uberjar {:aot :all}})
