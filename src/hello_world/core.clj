(ns hello-world.core)
;
; FIBONACCI?
; REPL Perf measurement:
; (use 'criterium.core)
; (with-progress-reporting (bench (showPrimesTo 1000) :verbose))
;
(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

(reduce (fn [primes num]

          ) (range 100))

(defn hasNotFraction [num div]
  (= 0 (mod num div))
  )

(defn isPrime [num]
  (not-any? #(hasNotFraction num %1) (range 2 num))
  )

(defn showAllTo [num]
  (map
    #(str %1 " - " (isPrime %1))
    [1 2 3 4 5 6 7 8 9 10 11]
    )
  )

(defn showPrimesTo [num]
  (reduce
    (fn [primes curr]
      (if (isPrime curr) (conj primes curr) primes)
      )
    []
    (take (- num 1) (iterate inc 2))
    )
  )