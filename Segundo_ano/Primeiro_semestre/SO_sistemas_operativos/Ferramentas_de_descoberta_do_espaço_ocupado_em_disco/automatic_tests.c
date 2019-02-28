#include<stdio.h>
#include<stdlib.h>
#include<string.h>

char *form_use = "./automatic_tests <./script_name> <directory1> <directory2> ... <last_directory>";

int main(int argc, char **argv){

   if(argc < 3){
      printf("How to use:\n%s\n", form_use);
      return EXIT_FAILURE;
   }

   char **arguments = malloc(0);
   int num_arguments;
   int max_size_each_arg = 200;

   char *actual = calloc(max_size_each_arg, sizeof(char));
   int final_size_all = 0;

   printf("Arguments:\n");
   for(num_arguments = 0; *actual != '\n'; num_arguments++){
      printf("> ");
      fgets(actual, max_size_each_arg, stdin);

      arguments = realloc(arguments, (num_arguments + 1)*sizeof(char *));
      arguments[num_arguments] = malloc(strlen(actual) + 1);
      final_size_all += strlen(actual) + 1;

      strcpy(arguments[num_arguments], actual);
      arguments[num_arguments][strlen(actual) - 1] = ' ';
   }
   num_arguments--;

   int size_args = 0;
   for(int num = 1; num < argc; num++) 
      size_args += strlen(argv[num]);

   int size_all = final_size_all + size_args + 1;
   char *all = calloc(size_all, sizeof(char));
   
   for(int num = 0; num < num_arguments; num++){
      *all = '\0';
      strcpy(all, argv[1]);
      all[strlen(all)] = ' ';
      all[strlen(all) + 1] = '\0';
      int a = strlen(all);
      for(int num2 = num; num2 < num_arguments; num2++){
         for(int num3 = num; num3 <= num2; num3++)
            strcat(all, arguments[num3]);
         for(int num2 = 2; num2 < argc; num2++){
            strcat(all, argv[num2]);
            all[strlen(all)] = ' ';
            all[strlen(all) + 1] = '\0';
         }
         printf("\n%s\n", all);
         system(all);
         memset(all + a, '\0', size_all);
      }
   }
}