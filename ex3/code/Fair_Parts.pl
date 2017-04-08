         
read_and_return(File, N, Weights):-
    open(File, read, Stream),
    read_line(Stream, [M, N]),
    read_line(Stream, Weights),
    length(Weights, L),
    ( L =:= M -> close(Stream)  %% just a check for for sanity
    ; format("Error: expected to read ~d weights but found ~d", [M, L]),
      close(Stream), fail
    ).

read_line(Stream, List):-
    read_line_to_codes(Stream, Line),
    atom_codes(A, Line),
    atomic_list_concat(As, ' ', A),
    maplist(atom_number, As, List).


accRev([H|T],A,R):-  accRev(T,[H|A],R).
   accRev([],A,A).
acc_rev_fast(L,R):-  accRev(L,[],R). 



list_sum([], 0).
list_sum([H|T], Sum) :-
   list_sum(T, Rest),
   Sum is H + Rest.


my_max([], R, R). 
my_max([X|Xs], WK, R):- X >  WK, my_max(Xs, X, R). 
my_max([X|Xs], WK, R):- X =< WK, my_max(Xs, WK, R).
my_max([X|Xs], R):- my_max(Xs, X, R). 

validate([], _, _, Cnt, Ret):-
    Ret is Cnt+1.
validate([L|Ls], Mid, Sum, Cnt, Ret):-
    New_Sum is Sum+L,
    (	New_Sum > Mid ->
        New_Cnt is Cnt+1,
        validate([L|Ls], Mid, 0      , New_Cnt, Ret)
    ;	validate(Ls    , Mid, New_Sum,     Cnt, Ret)
    ).


lower_bound(List,  Sum, N, Ret):-
my_max(List, 0, Res1),
A is div(Sum, N),
(	Res1 > A ->
        Ret = Res1
    ;	Ret = A
    ).
    
upper_bound(_, Max, Sum, 1, Ret):-
    (Max =< Sum ->	Ret = Sum ; Ret = Max).
upper_bound([L|Ls], Max, Sum, N, Ret):-
    L =< Max ->	(New_N is N-1,New_Sum is Sum-L,upper_bound(Ls, Max, New_Sum, New_N, Ret));
            (New_N is N-1,New_Sum is Sum-L,upper_bound(Ls, L  , New_Sum, New_N, Ret)).


binary_sear(_, X, Y, _, Res):- X =:= Y -> Res is div(X+Y,2) ,!.
binary_sear(List, Min, Max, N, Ret):-
    Mid is div(Min+Max, 2),
    validate(List, Mid, 0, 0, X),
    (	X =< N ->
        binary_sear(List, Min    , Mid, N, Ret)
    ;	New_Min is Mid+1,
        binary_sear(List, New_Min, Max, N, Ret)
    ).
    
    
    

result_ok([], _, _, []).
result_ok([L|Ls], N, C, Ret):-
    (	N == 0;
        L == -5;
        C == -5 ->
        Ret = [L|Rs],
        result_ok(Ls,     N, L, Rs)
    ;	New_N is N-1,
        Ret = [-5, L|Rs],
        result_ok(Ls, New_N, L, Rs)
    ).
    

output([],[[]]).
output([H|T],[[H|XH]|XR]) :- var(H),!,output(T,[XH|XR]).
output([-5|T],[[]|X]) :- !,output(T,X).
output([H|T],[[H|XH]|XR]) :- output(T,[XH|XR]).	

sane_ok([], _, _, []).
sane_ok([L|T], Mid, Sum, Ret):-
    Sum1 is Sum+L,
    (	Sum1 > Mid ->
        Ret = [-5|Rs],
        sane_ok([L|T], Mid, 0      , Rs)
    ;	Ret = [L|Rs],
        sane_ok(T, Mid, Sum1, Rs)
    ).





fair_parts(Input, Output):-
    read_and_return(Input, N, Weights),
    (	N =:= 1 ->[Weights]
    ;	list_sum(Weights, Sum),
        lower_bound(Weights, Sum,N, Min),
        upper_bound(Weights, 0, Sum,N, Max),
        acc_rev_fast(Weights, List),
        binary_sear(List, Min, Max, N, Mid),
        validate(List, Mid, 0, 0, X),
        New_N is N-X,
        sane_ok(List, Mid, 0, Fine),
        (	New_N > 0 ->
            result_ok(Fine, New_N, -1, Ret),
            acc_rev_fast(Ret, Rev)
        ;	acc_rev_fast(Fine, Rev)
        ),
        output(Rev, Output)
    ).
