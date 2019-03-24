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
(defn t_simple [init-data] (->>
                       init-data
                       (map #(clojure.string/join %))
                       (map #(re-find #"(?i)999" %))
                       (count)
                       )
  )

;"Elapsed time: 6040.449344 msecs"
(defn t_pmap_double [init-data] (->>
                            init-data
                            (pmap #(clojure.string/join %))
                            (pmap #(re-find #"(?i)999" %))
                            (count)
                            )
  )

;"Elapsed time: 5966.242608 msecs"
(defn t_pmap_single [init-data] (->>
                            init-data
                            (pmap (fn [arr] (re-find #"(?i)999" (clojure.string/join arr))))
                            (count)
                            )
  )

;"Elapsed time: 10466.158169 msecs"
(defn t_pmap_part [init-data] (->>
                          init-data
                          (partition 100)
                          (pmap #(map (fn [subarr] (clojure.string/join subarr)) %))
                          (pmap #(map (fn [subarr] (re-find #"(?i)999" subarr)) %))
                          (flatten)
                          (count)
                          )
  )


;"Elapsed time: 10326.207215 msecs"
(defn t_rmap [init-data] (->>
                     init-data
                     (r/map #(clojure.string/join %))
                     (r/map #(re-find #"(?i)999" %))
                     (r/foldcat)
                     (count)
                     )
  )
; tesser impl:
(defn t_tesser [init-data] (->>
                       (t/map #(clojure.string/join %))
                       (t/map #(re-find #"(?i)999" %))
                       (t/count)
                       (t/tesser (partition 500 init-data))

                       )
  )
