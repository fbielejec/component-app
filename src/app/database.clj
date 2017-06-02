(ns app.database
  (:require [com.stuartsierra.component :as component]))

(declare get-db-conf)

(defrecord Database [connection]
  component/Lifecycle
  ;; idempotent start
  (start [this]
    (if connection
      this
      (assoc this :connection "still here")))
  ;; idempotent stop
  (stop [this]
    (if (not connection)
      this
      (do
        (assoc this :connection nil)))))

(defn new-database
  "Returns a new instance of the component."
  []
  (map->Database {:connection nil}))
