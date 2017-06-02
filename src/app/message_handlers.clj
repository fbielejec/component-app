(ns app.message-handlers
  (:require [com.stuartsierra.component :as component]))

(defrecord Handlers [handlers database]
  component/Lifecycle
  ;; idempotent start
  (start [this]
    (if handlers
      this
      (assoc this :handlers "still here")))
  ;; idempotent stop
  (stop [this]
    (if (not handlers)
      this
      (do
        (assoc this :handlers nil)))))

(defn new-message-handlers
  "Returns a new instance of the component."
  []
  (map->Handlers {:handlers nil}))
