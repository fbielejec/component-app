(ns app.system
  (:gen-class)
  (:require [com.stuartsierra.component :as component]
            [app.message-handlers :as message-handlers]
            [app.database :as database]))

(defn system-map
  "Returns a new instance of the whole application. No side effects."
  []
  (component/system-map :database (database/new-database)
                        :message-handlers (message-handlers/new-message-handlers)))

(def dependency-map
  ;; List systems inter-dependencies in either:
  ;;    {:key [:dependency1 :dependency2]} form
  ;; or
  ;;    {:key {:name-arg1 :dependency1
  ;;           :name-arg2 :dependency2}} form
  {:message-handlers [:database]})

(defn create-system
  []
  (component/system-using (system-map)
                          dependency-map))

(defn start
  "Performs side effects to initialize the system, acquire resources,
  and start it running. Returns an updated instance of the system."
  [system]
  (component/start system))

(defn stop
  "Performs side effects to shut down the system and release its
  resources. Returns an updated instance of the system."
  [system]
  (component/stop system))

(defn -main [& args]
  (let [system (create-system)]
    (start system)))
