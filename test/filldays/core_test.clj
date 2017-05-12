(ns filldays.core-test
  (:require [clojure.test :refer :all]
            [filldays.core :refer :all]
            [filldays.time-utils :refer :all]
            [clj-time.core :as t]))

(testing "ifilld"
  (deftest should-return-no-date-when-equals
   (testing "should return empty list when same day"
     (let [day (String->Date "2017-5-17T00:00:00Z")]
       (is (= 0 (count (ifilld day day))))
       )
     )
   )

 (deftest should-return-no-date-when-0-day-gap
   (testing "should return empty list when 0 day gap"
     (let [day17 (String->Date "2017-5-17T00:00:00Z") day18 (String->Date "2017-5-18T00:00:00Z")]
       (is (= 0 (count (ifilld day17 day18))))
       )
     )
   )

 (deftest should-return-1-date-when-1-day-gap
   (testing "should return 2017-05-18T00:00:00Z when 1 day gap"
     (let [day17 (String->Date "2017-5-17T00:00:00Z") day19 (String->Date "2017-5-19T00:00:00Z") day18 (String->Date "2017-5-18T00:00:00Z")]
       (is (= (list day18) (ifilld day17 day19)))
       )
     )
   )

 (deftest should-return-2-dates-when-2-day-gap
   (testing "should return 2017-05-18T00:00:00Z and 2017-05-19T00:00:00Z when 2 day gap"
     (let [day17 (String->Date "2017-5-17T00:00:00Z") day19 (String->Date "2017-5-19T00:00:00Z") day18 (String->Date "2017-5-18T00:00:00Z") day20 (String->Date "2017-5-20T00:00:00Z")]
       (is (= (list day18 day19) (ifilld day17 day20)))
       )
     )
   )

 (deftest should-return-2-dates-when-2-day-gap-reverse-order
   (testing "should return 2017-05-18T00:00:00Z and 2017-05-19T00:00:00Z when 2 day gap reverse order"
     (let [day17 (String->Date "2017-5-17T00:00:00Z") day19 (String->Date "2017-5-19T00:00:00Z") day18 (String->Date "2017-5-18T00:00:00Z") day20 (String->Date "2017-5-20T00:00:00Z")]
       (is (= (list day18 day19) (ifilld day20 day17)))
       )
     )
   )
 )

(testing "mfilld"
  (deftest should-return-1-day-when-1-day
    (testing "Should return 2017-5-17T00:00:00Z"
      (let [day (String->Date "2017-5-17T00:00:00Z")]
        (is (= (list day) (mfilld (list day))))
        )
      )
    )

  (deftest should-return-2-days-when-2-days-no-gap
    (testing "Should return 2017-5-17T00:00:00Z and 2017-5-18T00:00:00Z when 2 consecutive days"
      (let [day17 (String->Date "2017-5-17T00:00:00Z") day18 (String->Date "2017-5-18T00:00:00Z")]
        (is (= (list day17 day18) (mfilld (list day17 day18))))
        )
      )
    )

  (deftest should-return-3-days-when-3-days-no-gap
    (testing "Should return 2017-5-17T00:00:00Z, 2017-5-18T00:00:00Z and 2017-5-19T00:00:00Z when 3 consecutive days"
      (let [day17 (String->Date "2017-5-17T00:00:00Z") day18 (String->Date "2017-5-18T00:00:00Z") day19 (String->Date "2017-5-19T00:00:00Z")]
        (is (= (list day17 day18 day19) (mfilld (list day17 day18 day19))))
        )
      )
    )

  (deftest should-return-6-days-when-3-days-x..x.x
    (testing "Should return 2017-5-17T00:00:00Z, 2017-5-18T00:00:00Z, 2017-5-19T00:00:00Z, 2017-5-20T00:00:00Z, 2017-5-21T00:00:00Z and 2017-5-22T00:00:00Z when (17..20.22)"
      (let [day17 (String->Date "2017-5-17T00:00:00Z") day18 (String->Date "2017-5-18T00:00:00Z") day19 (String->Date "2017-5-19T00:00:00Z") day20 (String->Date "2017-5-20T00:00:00Z") day21 (String->Date "2017-5-21T00:00:00Z") day22 (String->Date "2017-5-22T00:00:00Z")]
        (is (= (list day17 day18 day19 day20 day21 day22) (mfilld (list day17 day20 day22))))
        )
      )
    )
  )
