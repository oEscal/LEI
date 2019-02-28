#!/bin/bash

#variables
arguments=("$@")
toMake=()


#initial argument treatment
if [[ $# -lt 2 ]]; then
	echo "Numero de argumentos demasiado pequeno"
	exit -1
fi

#semaphore cleaning
source ./clean.sh	#NOTE: The file must have a correct "Semaphore Key"

#compile
cd ../src/
errors=0
for i in ${arguments[@]}; do
echo $i
	if [[ "$i" =~ ^[0-9]+$ ]]; then
		number=$i
	else
		case "$i" in
			"-e")
				rm errors.txt &> /dev/null
				mv logging.c ./backup.c
				mv logging2.c ./logging.c
				mv probDataStruct.h ./backupProcData.h
				mv probDataStruct2.h ./probDataStruct.h
				errors=1
				echo "errors"
				;;
			"gr")
				toMake=(gr)
				echo 2
				;;
			"wt")
				toMake=(wt)
				echo 3
				;;
			"ch")
				toMake+=(ch)
				echo 4
				;;
			"rt")
				toMake=(rt)
				echo 5
				;;
			"all")
				toMake=(all)
				echo 6
				;;
			"all_bin")
				toMake=(all_bin)
				echo 7
				;;
			"group")
				toMake+=(group)
				echo 2
				;;
			"waiter")
				toMake+=(waiter)
				echo 3
				;;
			"chef")
				toMake+=(chef)
				echo 4
				;;
			"receptionist")
				toMake+=(receptionist)
				echo 5
				;;
			esac
	fi
done

#if ! [[ $# -eq 2 ]]; then
#	sed -i "16s/.*/perso:\t${toMake[0]}\t${toMake[1]}\t${toMake[2]}\t${toMake[3]}\t${toMake[4]}\t${toMake[5]}/" Makefile #vai até à linha 16 do "MakeFile" e faz Insert do que aqui está
#	make perso
#else
#	make $lastArg
#fi

make "${toMake[@]}"
if [[ $errors -eq 1 ]]; then
	mv logging.c ./logging2.c
	mv backup.c ./logging.c
	mv probDataStruct.h ./probDataStruct2.h
	mv backupProcData.h ./probDataStruct.h
	errors=1
fi	


#execute
cd ../run
source ./run.sh $number
exit 1




