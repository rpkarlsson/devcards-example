(ns mck.cards.accordion
  (:require-macros
   [devcards.core :refer [defcard]])
  (:require
   [devcards.core :as dc]
   [reagent.core :as r]
   [re-frame.core :as rf]))

(defcard state
  re-frame.db/app-db)

(def closed {:max-height 0
             :overflow "hidden"
             :transition "max-height 400ms ease"})

(defn item-view
  [{:keys [id name description] :as item} selected]
  [:div {:on-click #(rf/dispatch [:select-item id])
         :style {:border-bottom "1px solid"
                 :cursor "pointer"
                 :padding "10px"}}
   [:h3 name]
   [:div {:style (merge {:max-height "0"
                         :overflow "hidden"
                         :transition "max-height 0.5s linear"}
                        (when selected
                          {:max-height "400px"}))}
    [:p description]
    [:button {:on-click #(rf/dispatch [:delete-item id])} "Ta bort"]]])

(defcard item-view-card
  "Should present an item inside a box."
  (r/as-element
   [:div
    [item-view {:id 1
                :name "Millenium Falcon"
                :description "Did the Kessel run in under 12 parsecs."}]
    [item-view {:id 1
                :name "Millenium Falcon"
                :description "Did the Kessel run in under 12 parsecs."}
     true]]))

(defn tick-view
  []
  [:p @(rf/subscribe [:query-tick])])

(defcard tick
  (r/as-element
   [tick-view]))

(defn items-view
  []
  [:div {:style {:border "1px solid"
                 :box-shadow "0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19)"
                 :border-bottom "0"
                 :border-radius "0.25rem"}}
   (let [items @(rf/subscribe [:query-items])
         selected (rf/subscribe [:query-selected-item])]
     (doall
      (map (fn [[id item]]
             ^{:key (str "item-" id)}
             [item-view item (= id @selected)])
           items)))])

(defcard items
  (r/as-element
   [items-view]))
