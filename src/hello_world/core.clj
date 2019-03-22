;(ns hello-world.core)
;
;(use 'criterium.core)
;(import java.lang.Math)
;(require '[clojure.core.reducers :as r])
;
;
;
;;
;; FIBONACCI?
;; REPL Perf measurement:
;; (use 'criterium.core)
;; (with-progress-reporting (bench (showPrimesTo 1000) :verbose))
;;
;; (use 'hello-world.core :reload-all)  (primeBenchmark 100)
;;
;
;(defn hasNotFraction [num div]
;  (= 0 (mod num div))
;  )
;
;; max number for division which has any sense
;(defn max-div-num [num] (inc (Math/ceil (Math/sqrt num))))
;
;;
;; different isPrime implementations
;;
;
;(defn isPrime0 [num]
;    (not-any? #(hasNotFraction num %1) (range 2 num)))
;
;; bugos, 2 nincs benne
;(defn isPrime1 [num]
;  (let [div (max-div-num num)]
;    (not-any? #(hasNotFraction num %1) (range 2 div))
;    )
;  )
;
;(defn isPrime1_1 [num]
;  (r/reduce (fn [acc curr] false) true
;            (r/take-while #(hasNotFraction num %1)
;                          (range 2 (max-div-num num))))
;  )
;
;; bugos, 7-tol indul
;(defn isPrime1_2 [num]
;  (reduce (fn [acc curr] false) true
;            (take-while #(hasNotFraction num %1)
;                          (range 2 (max-div-num num))))
;  )
;
;; bugos: mindent mutat
;(defn isPrime1_3 [num]
;  (reduce (fn [acc curr] false) true
;            (r/take-while #(hasNotFraction num %1)
;                          (range 2 (max-div-num num))))
;  )
;
;(defn isPrime2 [num]
;  (not-any? #(hasNotFraction num %1) (take (max-div-num num) (iterate inc 2)))
;  )
;
;; for perf test
;(defn isPrime3 [num]
;  (let [div (max-div-num num)]
;    (not-any? #(hasNotFraction div %1) [2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45 46 47 48 49 50 51 52 53 54 55 56 57 58 59 60 61 62 63 64 65 66 67 68 69 70 71 72 73 74 75 76 77 78 79 80 81 82 83 84 85 86 87 88 89 90 91 92 93 94 95 96 97 98 99 100])))
;
;
;; bugos, 2 nincs benne
;(defn isPrime4 [num]
;  (empty? (for [i (range 2 (max-div-num num)) :when (hasNotFraction num i)] i)))
;
;; bugos: mindent mutat
;(defn isPrime5 [num]
;  (empty? (for [i (range 2 (max-div-num num)) :while (= true (hasNotFraction num i))] i)))
;
;; bugos: mindent mutat
;(defn isPrime6 [num]
;  (empty? (for [i (range 2 (max-div-num num)) :while (< 0 (mod num i))] i)))
;
;; from net: https://stackoverflow.com/questions/27321123/performance-difference-in-clojure-for-versus-loop
;(defn is-prime-for? [n]
;  (empty? (for [i (range 2 (max-div-num n))
;                :when (= 0 (rem n i))]
;            i)))
;
;; from net: https://stackoverflow.com/questions/27321123/performance-difference-in-clojure-for-versus-loop
;(defn is-prime-loop? [n]
;  (let [div (max-div-num n)]
;  (loop [i 2]
;    (cond (> i div) true
;          (zero? (rem n i)) false
;          :else (recur (inc i)))))
;  )
;
;
;(defn isPrimeBench  [num]
;(map (fn [[k v]]
;       (println "** " k)
;       (quick-bench (v num))
;       ) {
;                  "is-prime-for?" is-prime-for?
;                  "is-prime-loop?" is-prime-loop?
;                  "isPrime1" isPrime1
;                  "isPrime1_1" isPrime1_1
;                  "isPrime1_2" isPrime1_2
;                  "isPrime1_3" isPrime1_3
;                  "isPrime2" isPrime2
;                  ;"isPrime3" isPrime3
;                  "isPrime4" isPrime4
;                  ;"isPrime5" isPrime5
;                  ;"isPrime6" isPrime6
;                  })
;)
;
;
;(defn showPrimesTo [prime-fn num]
;  (reduce
;    (fn [primes curr]
;      (if (prime-fn curr) (conj primes curr) primes)
;      )
;    []
;    (take (- num 1) (iterate inc 2))
;    )
;  )
;
;(defn allPrimeBench  [num]
;(map (fn [[k v]]
;       (println "** " k)
;       (quick-bench (showPrimesTo v num))
;       ) {
;                  "is-prime-for?" is-prime-for?
;                  "is-prime-loop?" is-prime-loop?
;                  "isPrime1" isPrime1
;                  "isPrime1_1" isPrime1_1
;                  "isPrime1_2" isPrime1_2
;                  "isPrime1_3" isPrime1_3
;                  "isPrime2" isPrime2
;                  ;"isPrime3" isPrime3
;                  "isPrime4" isPrime4
;                  ;"isPrime5" isPrime5
;                  ;"isPrime6" isPrime6
;                  })
;)
;
;
;(defn showAllTo [num]
;  (map
;    #(str %1 " - " (isPrime1 %1))
;    [1 2 3 4 5 6 7 8 9 10 11]
;    )
;  )
;
;(defn -main [& args]
;  (allPrimeBench 1003)
;  )
