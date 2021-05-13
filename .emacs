;; .emacs

;; add and configure line numbers and column numbers
(setq line-number-mode t)
(setq column-number-mode t)
(global-linum-mode 1)
(setq linum-format "%d ")

;; set a dedicated directory for backup files
(setq backup-directory-alist `(("." . "~/.saves")))

;; no tab characters in whitespace
(setq-default indent-tabs-mode nil)

;; handle indentation with 4 white spaces
(setq-default c-default-style "linux"
              c-basic-offset 4)
(setq-default tab-width 4)
(setq indent-line-function 'insert-tab)

;; handle multi-line inline lambda expressions
(setq c-offsets-alist '((arglist-cont-nonempty . 0)))

;; auto remove trailing white space
(add-hook 'before-save-hook 'delete-trailing-whitespace)

;; highlight lines that exceed some column limit
(setq-default
 whitespace-line-column 100
 whitespace-style '(face lines))
(add-hook 'prog-mode-hook #'whitespace-mode)

(custom-set-variables
 ;; uncomment to always end a file with a newline
 ;'(require-final-newline t)
 ;; uncomment to disable loading of "default.el" at startup
 ;'(inhibit-default-init t)
 ;; default to unified diffs
 '(diff-switches "-u"))

;;; uncomment for CJK utf-8 support for non-Asian users
;; (require 'un-define)
