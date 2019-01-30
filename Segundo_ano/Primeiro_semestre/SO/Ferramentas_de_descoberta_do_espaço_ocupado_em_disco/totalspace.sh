#!/bin/bash
#$1 é a diretoria a ser analizada
#$2 é -n
#$3 é -l
#$4 é -d
#$5 é -L

function seeDirs(){ 
	IFS=$'\n'			
   path="$1"
	l=$3
   sum=0
	NA=1
	
	ls $path &> /dev/null || NA=0	#tratamento de erros
	if [[ $NA -ne 0 && $(ls $path) != "" ]]; then
		ls -d "$path"/* &> /dev/null || NA=0	#tratamento de erros
		#/$ significa que vai só ver se a string passada tem ou não / no final
		if [[ $NA -ne 0 && $(ls -Spd "$path/"* | grep -v /$) != "" ]]; then	#-S é para ordenar por tamanho e -p é para adicionar / no final das diretorias
      	files=($( stat --printf="%n\n" $(ls -Spd "$path/"* | grep -v /$) ))
			
      	array_files=()
			for i in ${files[@]}; do	#tratamento da opção -n
      	   if [[ $( echo $i | rev | cut -d "/" -f1 | rev ) =~ $2 ]]; then
					array_files=("${array_files[@]}" "$i")
      	   fi
   		done

   		array_date=();
			for i in ${array_files[@]}; do	#tratamento da opção -d
      	   actual="$(stat -c %x $i)"
				file_date=$(date -d "$actual" +%s)
				compare_date=$(date -d "$4" +%s)
				if [[ $file_date -le $compare_date ]]; then	
					array_date+=($i)
				fi
			done;

			array_files=(${array_date[@]});
			if [[ $5 != "" && ${#array_files[@]} -ne 0 ]]; then	#tratamento da opção -L
				for ((num=0; num<$5; num++)); do
					if [[ ${array_files[num]} == "" ]]; then
						break		
					fi
					bigger=(${bigger[@]} "${array_files[num]}")
				done	
				bigger=($(ls -S $(printf "%s\n" "${bigger[@]}")))	

				new_bigger=()										
				for ((num=0; num<$5; num++)); do
					new_bigger=(${new_bigger[@]} ${bigger[num]})	
				done 
				bigger=(${new_bigger[@]})		

			elif [[ $5 == "" ]]; then	#tratamento da opção -l
   			size=()	

   			if [[ ${#array_files[@]} -gt 0 ]]; then
					stat --printf="%s\n" "${array_files[@]}" &> /dev/null || NA=0
   			   if [[ NA -ne 0 ]]; then
						size=($( stat --printf="%s\n" "${array_files[@]}"))
					fi
   			fi

				if [[ $l == "" || $l -gt ${#size[@]} ]]; then
					l=${#size[@]}
				fi

				for ((num=0; num<$l; num++)); do
					if [[ ${size[num]} != "" ]]; then
						sum=$(($sum+${size[num]}))
					fi
				done
			fi
		fi
	fi

	if [[ $NA -eq 0 ]]; then
		sum=-1
	fi

   for i in "$path/"*; do
      if [[ -d "$i" ]]; then
         path_matrix[${#path_matrix[@]}]="$path"   #stak para as diretorias
         sum_matrix[${#sum_matrix[@]}]=$sum        #stak para as somas
         seeDirs "$i" "$2" "$3" "$4" "$5"
         
         index=$((${#path_matrix[@]}-1))				
         path="${path_matrix[$index]}"
         path_matrix=("${path_matrix[@]:0:$index}")
			if [[ $NA -ne 0 && ${sum_matrix[$index]} -ge 0 ]]; then
         	sum=$(($sum+${sum_matrix[$index]}))
			else
				sum=-1
			fi
         sum_matrix=("${sum_matrix[@]:0:$index}")
      fi
   done
   
	if [[ $5 != "" && ${#bigger[@]} -ne 0 ]]; then
		result=$(stat --printf="%s %n\n" "${bigger[@]}")
	else
      result[${#result[@]}]="$({
			if [[ $sum -ge 0 ]]; then
				echo $sum
			else
				echo NA
			fi
		}) ${path}"
   fi
}

function verify(){
	if [[ -z "${1}" ]]; then
	#se nao existir esse argumento, retorna falso
		return 1;
	fi;
	#else retorna true
	return 0;
}

arguments=("$@")

num=0

n=""
l=""
d=$(date '+%Y-%m-%d %H:%M:%S')
L=""
r=1
a=1

for i in "${arguments[@]}"; do
   if [[ ignore -ne 1 ]]; then
      num=$(($num+1))
      case $i in
         "-n")
            n=${arguments[num]}
            if ! verify "$n"; then	
            	echo -e "ERRO!\nOpção -n sem valor!"
            	exit 1
            fi
            ignore=1
         ;;
      	"-l")
			   l=${arguments[num]}
			   if ! verify "$l" || verify "$L"; then
			   	echo -e "ERRO!\nTentativa de uso das opções -l e -L em simultânio!"
               exit 1
            	fi
            	if ! [[ "$l" =~ ^[0-9]+$ ]]; then
					echo -e "ERRO!\nFormato de arguments invalido"
					exit 1
				fi;
			   ignore=1
		   ;;
		   "-d")
				d=${arguments[num]}
				ignore=1
			   if ! verify "${arguments[num]}"; then
			   	echo -e "ERRO!\nOpção -d sem valor!"
            		exit 1
            fi
         ;;
         "-L")
			   L=${arguments[num]}
			   if ! verify "$L" || verify "$l"; then
			   	echo -e "ERRO!\nTentativa de uso das opções -l e -L em simultânio!"
               exit 1
            fi
			  if ! [[ "$L" =~ ^[0-9]+$ ]]; then
					echo -e "ERRO!\nFormato de arguments invalido"
					exit 1
				fi;
			   ignore=1
		   ;;
		   "-r")
			   r=0
		   ;;
         "-a")
			   a=0
		   ;;
			*)
				path+=($i)
		   ;;
      esac
   else
      ignore=0;
      num=$(($num+1))
   fi
done

for i in ${path[@]}; do
	seeDirs "$i" "$n" "$l" "$d" "$L"
done

#tratamento de -r e -a
if [[ $a -eq 0 ]]; then
	if [[ $r -eq 1 ]]; then				
		printf "%s\n" "${result[@]}" | sort -k2 
	else								
		printf "%s\n" "${result[@]}" | sort -k2 -r
	fi
elif [[ $r -eq 0 ]]; then			
	printf "%s\n" "${result[@]}" | sort -n 
else 									
	printf "%s\n" "${result[@]}" | sort -n -r
fi