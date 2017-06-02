(defproject component-app "0.0.1-SNAPSHOT"
  :description "Demonstarte a possible problem with component lifecycle methods."
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [com.stuartsierra/component "0.3.0"]]
  :main app.system
  :source-paths ["src/"]
  :repl-options {:init-ns ^:skip-aot user}
  :profiles {:dev {:source-paths ["dev"]
                   :dependencies [[org.clojure/tools.namespace "0.2.10"]]}
             :test {}})
