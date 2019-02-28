#ifndef SEM_ERRORS
#define SEM_ERRORS

#include<stdio.h>

/*
*  Arguments
*     value -> value returned by semUp() or semDown functions
*     op -> semaphore operation:
*        > 0 if up
*        > 1 if down
*     ent -> identify entity:
*        > 0 if chef
*        > 1 if group
*        > 2 if waiter
*        > 3 if receptionist
*/
void verifySemError(int value, int op, int ent){
   if(op < 0 || op > 1 || ent < 0 || ent > 3){
      perror ("Error using verifySemErrorChefUp() function (access handle_semaphore_access.h for more informations)");
      exit (EXIT_FAILURE);
   }

   char entities[][13] = {"Chef", "Group", "Waiter", "Receptionist"};
   char operations[][5] = {"up", "down"};
   if(value == -1){
      fprintf (stderr, "Error on the %s operation for semaphore access (%s)\n", operations[op], entities[ent]);
      exit (EXIT_FAILURE);
   }
}

#endif