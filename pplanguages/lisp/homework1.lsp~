;Author Oscar Menendez
;Lisp assignment 1
;Principles of programming languages

;1)delete a member of the list(l) at index (n)
(defun deleteAt(l n)
	(cond ((or (<= n 0) (null l)) (cdr l))
  	(t (cons (car l) (deleteAt (cdr l) (- n 1) )))))

;2)Delete all elements of list (l) at indexes d
(defun deleteAllAt(l &rest d) 
	(cond ((null l) (cdr l))
	(t (deleteAllHpr (deleteAt l (car d)) (sub (cdr d) 1)))))

;Delete all helper
(defun deleteAllHpr(l d) 
	(cond ((or (null l) (null d)) l)
	(t (deleteAllHpr (deleteAt l (car d)) (sub (cdr d) 1 )))))

;subtracts n from every element in the list l
(defun sub(l n)
	(cond ((null l) l) 
	(t (cons (- (car l) n) (sub (cdr l) n)))))

;3)function sumAll that will return the sum of the values of its arguments.
(defun sumAll(&rest x) 
	(cond ((null (car x)) 0)
	(t (sumHpr (cons (car x) (cdr x))))))

;sum helper function for sumAll
(defun sumHpr(l) 
	(cond ((null l) 0)
      	((numberp (car l))  (+ (car l) (sumHpr (cdr l))))
      	(t (+ (eval (car l)) (sumHpr (cdr l))))))

;4)function similar takes two lists,
; and returns a list of elements that are in both lists.
(defun similar(x y)
	(cond ((null x) x)
      	((null y) y)
      	((equal t (sim (car x) y)) (cons (car x) (similar (cdr x) y)))
      	(t (similar (cdr x) y))))

;sim helper function for similar
(defun sim(num y) 
	(cond ((null y) nil)
      	((equal num (car y)) t)
     	 (t (sim num (cdr y)))))

;5)function alone that takes two lists,
;returns a list of elements that aren't in both lists.
(defun alone(l1 l2)
	(cond ((null l1) l2)
        ((null l2) l2)
        (t (aloneHpr (append l1 l2) (similar l1 l2)))))

;alone helper function l1 combined lists, l2 similar elements
(defun aloneHpr(l1 l2)
 (cond ((null l1) nil)
       ((null l2) l1)
       (t (aloneHpr (rmElement l1 (car l2)) (cdr l2)))))

;remove element e form list l
(defun rmElement(l e)
        (cond ((null (car l)) (cdr l))
	((equal (car l) e) (rmElement (cdr l) e)) 
        (t (cons (car l) (rmElement (cdr l) e)))))

;6)function that takes a list as an argument and returns the last element of the list.
(defun lastElement(l) 
	(cond ((= (length l) 1) (car l))
      	((> (length l) 1) (lastElement (cdr l)))))

;7)function mcons that takesany number of arguments.
;  For example, (mcons 'a 'b 'c '(d e)) should return (a b c d e).
(defun mcons(&rest x)
	(cond ((null (car x)) x)
	(t (mconHpr x))))

;helper for mcons function,
;takes a list representing all the arguments.
(defun mconHpr(l) 
	(cond ((null (cdr l)) (car l))	
	(t (cons (car l) (mconHpr (cdr l))))))

;8)function nth should use the int as an index into every sublist,
;and return a list of the elements in the sublists at that position.
(defun mth(l n)
	(cond ((null (car l)) l)
	(t (cons (mthpr (car l) n) (mth (cdr l) n)))))

;helper for mth, change (cond (= 1 n)) to (cond (= 0 n))
;for zero base index
(defun mthpr(l n) 
	(cond ((= 1 n) (car l)) 
	(t (mthpr (cdr l) (- n 1)))))

