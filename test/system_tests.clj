(ns app.system-tests
  (:require [clojure.test :refer :all]
            [app.system :as system]))

;; uncomment l. 12 makes test pass
(deftest system-tests
  (testing "system/stop releases all resources"
    (let [the-system (system/create-system)]
      (do
        (-> (-> the-system system/start
                system/stop
                #_system/stop
                :message-handlers
                :database
                :connection)
            nil?
            is)))))
