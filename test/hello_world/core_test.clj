(ns hello-world.core-test
  (:require [clojure.test :refer :all]
            [hello-world.core :refer :all]))

(deftest has-fraction-test
  (testing "3/4 has a fraction"
    (is (= false (hasNotFraction 3 4))))

  (testing "9/3 has not a fraction"
    (is (= true (hasNotFraction 9 3))))
  )

(deftest is-prime-test
  (testing "3 is prime"
    (is (= true (isPrime 3))))

  (testing "4 is not prime"
    (is (= false (isPrime 4))))

  (testing "23 is prime"
    (is (= true (isPrime 23))))

  )