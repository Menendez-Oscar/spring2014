;calculates the factorial of a number n
(defun factorial(n) (if (= n 1) 1
			 (* n (factorial(- n 1)))
))

;calculates the combination of two numbers k and r
(defun combination(k r) (if (= r 1) k
			    (/ (factorial k) 
			    (* (factorial r) (factorial (- k r)))
)))

;probability that exactly r given user are active out of k total users with given probability p
(defun exactM(p k r)
  (* (* (combination k r) (expt p r)) (expt (- 1 p) (- k r)))
)

;probability than <= n users will be active
(defun lessThanM(n p k) (loop for i from 0 to n for
			x = (+ x (exactM p k i)) do (print)))

;probability than >= n users will be active
(defun moreThanM(n p k) (if (< n 0) 1
			 (- 1 (lessThanM n p k))))
