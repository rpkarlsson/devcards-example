(ns mck.cards
  (:require
    [devcards.core]
    [re-frame.core :as rf]
    [mck.db]
    [mck.cards.accordion]))

(defonce init-state (do (rf/dispatch-sync [:initialize])))

(devcards.core/start-devcard-ui!)
