#!/bin/sh

N='' # Size of array
P='' # Number of processes
S='' # Asynchronous flag
B='' # Buffered flag
M='' # Multiplier
T='' # Thread count
O='' # Pthread flag
G='' # Generate flag
E='' # Timer flag
H='' # Help flag (Prints usage)
V='' # Verbose flag

while getopts 'n:p:abm:t:ogehv' flag; do
  case "${flag}" in
    n) N="${OPTARG}" ;;
    p) P="-np ${OPTARG}" ;;
    a) S='-a' ;;
  	b) B='-b' ;;
  	m) M="-m ${OPTARG}" ;;
    t) T="-t ${OPTARG}" ;;
    o) O="-o" ;;
    g) G="-g" ;;
    e) E="-e" ;;
    h) H='-h' ;;
  	v) V='true' ;;
    *) error "Unexpected option ${flag}" ;;
  esac
done

file_a='data/matrix_'$N'_a.txt'
file_b='data/matrix_'$N'_b.txt'
N="-n $N"
run="mpirun $P -hostfile ../rlogin_nodes ./prob1 $N $S $B $M $T $O $G $E $H -- $file_a $file_b"

if [ "$V" = 'true' ]; then
	echo $run
fi

eval $run
