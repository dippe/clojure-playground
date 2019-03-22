(ns learn.mapregex
  ;(:require [tesser.core :as tesser])
  ;(:require [clojure.core/reducers :as r])
  )

(require '[clojure.core.reducers :as r])
(require '[tesser.core :as t])
;(require '[tesser.core :as tesser])

;(use 'tesser.core :as tesser)

; normal impl
;"Elapsed time: 10400.248806 msecs"
(defn t_simple [num] (->>
                       (repeat num (range 1100))
                       (map #(clojure.string/join %))
                       (map #(re-find #"(?i)999" %))
                       (count)
                       )
  )

;"Elapsed time: 6040.449344 msecs"
(defn t_pmap_double [num] (->>
                            (repeat num (range 1100))
                            (pmap #(clojure.string/join %))
                            (pmap #(re-find #"(?i)999" %))
                            (count)
                            )
  )

;"Elapsed time: 5966.242608 msecs"
(defn t_pmap_single [num] (->>
                            (repeat num (range 1100))
                            (pmap (fn [arr] (re-find #"(?i)999" (clojure.string/join arr))))
                            (count)
                            )
  )

;"Elapsed time: 10466.158169 msecs"
(defn t_pmap_part [num] (->>
                          (repeat num (range 1100))
                          (partition 100)
                          (pmap #(map (fn [subarr] (clojure.string/join subarr)) %))
                          (pmap #(map (fn [subarr] (re-find #"(?i)999" subarr)) %))
                          (flatten)
                          (count)
                          )
  )


;"Elapsed time: 10326.207215 msecs"
(defn t_rmap [num] (->>
                     (repeat num (range 1100))
                     (r/map #(clojure.string/join %))
                     (r/map #(re-find #"(?i)999" %))
                     (r/foldcat)
                     (count)
                     )
  )
; tesser impl:
(defn t_tesser [num] (->>
                       (t/map #(clojure.string/join %))
                       (t/map #(re-find #"(?i)999" %))
                       (t/count)
                       (t/tesser (partition 500 (repeat num (range 1100))))

                       )
  )
