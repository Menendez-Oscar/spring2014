(defun sum (list) 
    (if
        (null (car list)) 0
        (+ (car list) (sum (cdr list)))                                
))

(defun squareSum(list)
 (if
        (null (car list)) 0
        (+ (expt (car list) 2) (squareSum (cdr list)))
))


