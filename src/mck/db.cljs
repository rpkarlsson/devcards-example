(ns mck.db
  (:require
   [re-frame.core :as rf]))

#_(defonce interval (js/setInterval #(rf/dispatch [:tick]) 1000))

(comment
  (js/clearInterval interval))


;; Events
(rf/reg-event-fx
 :tick
 (fn [{:keys [db]} [_]]
   {:db (update db :tick inc)}))

(rf/reg-event-fx
 :delete-item
 (fn [{:keys [db]} [_ item-id]]
   {:db (update db :items dissoc item-id)}) )

(rf/reg-event-fx
 :select-item
 (fn [{:keys [db]} [_ id]]
   {:db (assoc db :selected-item id)}))

(rf/reg-event-db
  :initialize
  (fn [_ _]
    {:selected-item nil
     :tick 0
     :items {1 {:id 1
                :name "Admiral Ackbar "
                :description "Commands the Rebel fleet in their attack against the second Death Star in Return of the Jedi. Ackbar is a Mon Calamari leader and military commander who fought in the Clone Wars. He devotes himself to the cause of galactic freedom and becomes the foremost military commander of the Rebel Alliance, and later the New Republic. He later works alongside General Leia Organa as part of the Resistance in The Force Awakens. He is killed along with many Resistance leaders in The Last Jedi."}
             2 {:id 2
                :name "Leia Organa"
                :description "Leader in the Rebel Alliance, the New Republic, and the Resistance. She is the biological daughter of Anakin Skywalker and Padmé Amidala, as well as Luke Skywalker's twin sister, Han Solo's wife and Kylo Ren's mother."}
             3 {:id 3
                :name "Luke Skywalker "
                :description "Jedi whose coming of age and rise as a Jedi are portrayed in the original Star Wars trilogy. Son of Anakin Skywalker and Padmé Amidala, and Leia Organa's twin brother"}
             4 {:id 4
                :name "Han Solo"
                :description "Captain of the Millennium Falcon who joins the Rebellion and marries Leia Organa. He is murdered by his son, Kylo Ren, after a failed attempt to turn his son from the dark side."}}}))


;; Subs
(rf/reg-sub
 :query-items
 (fn [db v] (:items db)))

(rf/reg-sub
 :query-selected-item
 (fn [db v] (:selected-item db)))

(rf/reg-sub
 :query-tick
 (fn [db v] (:tick db)))
