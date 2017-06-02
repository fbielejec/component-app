(ns user
  "Namespace to support hacking at the REPL."
  (:require [app.system :as system]
            [clojure.tools.namespace.repl :refer (refresh set-refresh-dirs)]))

(remove-method clojure.core/print-method com.stuartsierra.component.SystemMap)

(defonce the-system
  ;; "A container for the current instance of the application.
  ;; Only used for interactive development."
  ;;
  ;; Don't want to lose this value if this file is recompiled (when
  ;; changes are made to the useful additional utilities for the REPL
  ;; at the end of the file), so use `defonce`.
  ;; But note that this /is/ blatted when a `reset` is done.
  nil)

;; Functions to manipulate the-system global var

(defn init
  "Creates a system and makes it the current development system."
  []
  (alter-var-root #'the-system
                  (constantly (system/create-system))))

(defn start
  "Starts the current development system."
  []
  (alter-var-root #'the-system system/start))

;; TODO: bug (or feature?) in component:
;; dependencies are not stopped
;; need to call stop twice in REPL
(defn stop
  "Shuts down and destroys the current development system."
  []
  (alter-var-root #'the-system
                  (fn [s] (when s (system/stop s)))))

(defn run
  "Creates a system, makes it the current development system and starts it."
  []
  (init)
  (start))

(defn restart
  "Stop and run again."
  []
  (stop)
  (start))

(defn reset
  "Stop, refresh, and run again."
  []
  (set-refresh-dirs "src" "dev")
  (stop)
  (refresh :after 'user/run))

