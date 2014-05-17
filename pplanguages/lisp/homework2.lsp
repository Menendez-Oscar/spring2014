;Oscar Menendez
;homework 2
;principles of programming laguages

(setq tasks '((purchase_lot 2) (design_house 5) (get_permit 1 purchase_lot design_house) (get_bids 14 purchase_lot design_house) (select_subs 2 get_bids) (excavate 1 get_permit select_subs) (construct_basement 7 excavate) (order_windows_doors 3 purchase_lot design_house) (get_windows_doors 10 order_windows_doors) (frame 12 get_permit select_subs) (rough_plumbing 5 frame) (rough_electric 3 frame) (roof 4 frame) (install_windows_doors 7 get_windows_doors rough_plumbing rough_electric) (vapor_barrier_insulation 2 roof install_windows_doors) (drywall 5 vapor_barrier_insulation) (inside_paint 3 drywall) (cupboards 3 inside_paint) (carpet_floor 5 inside_paint) (lights 2 inside_paint) (plumbing_heating 6 inside_paint) (siding 2 roof install_windows_doors) (outside_paint 3 siding) (move_house 1 cupboards carpet_floor lights plumbing_heating outside_paint) (connections 2 construct_basement move_house) (landscape 4 construct_basement move_house)))

;#1) sum take list of tasks return total time
(defun sum (l)
	(cond ((null (car l)) 0)
      	(t(+ (cadar l) (sum (cdr l))))))

;#2) predecessors takes x is a given specific job, l list of tasks 
(defun pred (x l)
	(cond ((null (car l)) nil)
	((equal (caar l) x) (cddar l))
	(t(pred x (cdr l)))))

;#3) gettime takes x a given job , l is list of tasks return time that job takes
(defun gettime(x l)
	(cond ((null (car l)) (print "job not found!"))
	((equal (caar l) x) (cadar l))
	(t(gettime x (cdr l)))))

;#4)  get_all_preds takes x a given job, l is a list of tasks
; returns all predecessors, it includes the job passed.
(defun get_all_preds(x l)
	(cond ((null x) nil)
	((listp x) (all_predshpr x l))
	(t (remove-duplicates (append (pred x l) (get_all_preds (pred x l) l))))))

;helper for #4
(defun all_predsHpr(x l)
	(cond ((null (car x)) x)
	(t (append (get_all_preds (car x) l) (all_predsHpr (cdr x) l)))))

;#5) precedes-takes 2 jobs and the list(l),
; returns true  if the first job (x)
; precedes the other(y) and nil otherwise.
(defun precedes(x y l) 
	(cond ((null (car l)) nil)
	(t (preHpr x (get_all_preds y l)))))

;helper for #5 p is the list of predecessors.
(defun preHpr (x p) 
	(cond ((null (car p)) nil)
	((equal x (car p)) t)
	(t (preHpr x (cdr p)))))

;#6) start day takes a job and list of tasks,
; returns the day the job can start
(defun start_day(x l)
	(cond ((null x) 0)
	(t (+ (gettime x l) (get_max (pred x l) l)))))
	
;start_day helper	
(defun starthpr (j jobl l)
	(cond ((null (pred x l)) (+ (gettime x l) 0))
	(t (+ (gettime x l) (get_max (get_all_preds x l) l)))))

;#7) get_max - takes a list of job names,
; and the list of tasks then returns a list with the time,
; and the job that finishes at the greatest time.
(defun get_max (jobl l)
(cond ((null jobl) nil)
(t (maxhpr (car jobl) (cdr jobl) l))))

;max helper
(defun maxhpr (j jobl l) 
(cond ((null jobl) nil)
((and (null (cdr jobl)) (> (car jobl) j)) (list (starthpr (car jobl) jobl l)))
((null (cdr jobl)) (list (start_day j l) j))
((and (< (gettime (car jobl) l)(gettime (cadr jobl) l)) (> (gettime (cadr jobl) l) (gettime j l))) (starthpr (cadr jobl) (cdr jobl) l))
((and (> (gettime (car jobl) l)(gettime (cadr jobl) l)) (> (gettime (car jobl) l) (gettime j l))) (starthpr (car jobl) (cdr jobl) l))
((and (< (gettime (car jobl) l)(gettime (cadr jobl) l)) (< (gettime (cadr jobl) l) (gettime j l))) (starthpr (cadr jobl) (cdr jobl) l))
((and (> (gettime (car jobl) l)(gettime (cadr jobl) l)) (< (gettime (car jobl) l) (gettime j l))) (starthpr (cadr jobl) (cdr jobl) l))))
	