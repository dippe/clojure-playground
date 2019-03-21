(ns hello-world.tesser-test
      :require '[tesser.core :as t])


(require '[tesser.core :as t])
(require '[clojure.core.reducers :as r])



; normal impl
;"Elapsed time: 10400.248806 msecs"
(time (->>
        (repeat 100000 (range 1100))
        (map #(clojure.string/join %))
        (map #(re-find #"(?i)999" %))
        (count)
        )
      )

;"Elapsed time: 6040.449344 msecs"
(time (->>
        (repeat 100000 (range 1100))
        (pmap #(clojure.string/join %))
        (pmap #(re-find #"(?i)999" %))
        (count)
        )
      )

;"Elapsed time: 5966.242608 msecs"
(time (->>
        (repeat 100000 (range 1100))
        (pmap (fn [arr] (re-find #"(?i)999" (clojure.string/join arr))) )
        (count)
        )
      )

;"Elapsed time: 10466.158169 msecs"
(time (->>
        (repeat 100000 (range 1100))
        (partition 100)
        (pmap #(map ( fn [subarr] (clojure.string/join subarr)) %))
        (pmap #(map ( fn [subarr] (re-find #"(?i)999" subarr)) %))
        (flatten)
        (count)
        )
      )


;"Elapsed time: 10326.207215 msecs"
(time (->>
        (repeat 100000 (range 1100))
        (r/map #(clojure.string/join %))
        (r/map #(re-find #"(?i)999" %))
        (r/foldcat)
        (count)
        )
      )
; tesser impl:
(time (->>
        (t/map #(clojure.string/join %))
        (t/map #(re-find #"(?i)999" %))
        (t/count)
        (t/tesser (partition 500 (repeat 100000 (range 1100))))
        )
      )