/**
 *  \file logging.c (implementation file)
 *
 *  \brief Problem name: Restaurant
 *
 *  \brief Logging the internal state of the problem into a file.
 *
 *  Defined operations:
 *     \li file initialization
 *     \li writing the present full state as a single line at the end of the file.
 *
 *  \author Nuno Lau - December 2018
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

#include <sys/types.h>
#include <unistd.h>


#include "probConst.h"
#include "probDataStruct.h"


//file with errors
static FILE *f_errors;

/* internal functions */

static FILE *openLog(char nFic[], char mode[])
{
    FILE *fic;
    char *fName;                                                                                      /* log file name */

    if ((nFic == NULL) || (strlen (nFic) == 0)) {
        return stdout;
    }
    else  {
        fName = nFic;
    }

    fprintf(stderr,"%d opening log %s %s\n",getpid(),nFic,mode);

    if ((fic = fopen (fName, mode)) == NULL) {
        perror ("error on opening log file");
        exit (EXIT_FAILURE);
    }
    return fic;
}

static void closeLog(FILE *fic)
{
    if(fic==stderr || fic == stdout) {
         fflush(fic);
         return;
    }

    if (fclose (fic) == EOF) {
        perror ("error on closing of log file");
        exit (EXIT_FAILURE);
    }
}

static void printHeader(FILE *fic, FULL_STAT *p_fSt)
{
    fprintf(fic,"%3s","CH");
    fprintf(fic,"%3s","WT");
    fprintf(fic,"%3s","RC");
    fprintf(fic," ");
    int g;
    for(g=0; g < p_fSt->nGroups; g++) {
        fprintf(fic," %s%02d","G",g);
    }

    fprintf(fic,"%5s","gWT");

    for(g=0; g < p_fSt->nGroups; g++) {
        fprintf(fic," %s%02d","T",g);
    }

    fprintf(fic,"\n");
}

/* external functions */

/**
 *  \brief File initialization.
 *
 *  The function creates the logging file and writes its header.
 *  If <tt>nFic</tt> is a null pointer or a null string, stdout is used.
 *
 *  The file header consists of
 *       \li a title line
 *       \li a blank line.
 *
 *  \param nFic name of the logging file
 */
void createLog (char nFic[], FULL_STAT *p_fSt)
{

    /*************************FOR ERRORS HANDLING*************************/
    //initialize params that save if a error was previously triggered
    p_fSt->error_chef_state = 0;
    p_fSt->error_waiter_state = 0;
    p_fSt->error_receptionist_state = 0;
    p_fSt->error_group_state = 0;
    p_fSt->error_groups_waiting = 0;
    p_fSt->error_assigned_table = 0;
    p_fSt->error_groups_waiting_cont = 0;
    p_fSt->error_group_state_cont = 0;
    p_fSt->error_assigned_table_number = 0;
    p_fSt->error_group_vs_receptionist_stat = 0;
    p_fSt->error_group_vs_waiter_stat = 0;
    p_fSt->error_group_vs_chef_stat = 0;

    //initialize params that save privious state
    p_fSt->prev_chef_stat = -1;
    p_fSt->prev_waiter_stat = -1;
    p_fSt->prev_receptionist_stat = -1;
    p_fSt->prev_groups_waiting = -1;
    for(int n = 0; n < MAXGROUPS; n++){
        p_fSt->prev_assigned_table[n] = -1;
        p_fSt->prev_group_stat[n] = -1;
        p_fSt->group_vs_receptionist_stat[n] = -1;
        p_fSt->group_vs_waiter_stat[n] = 0;
        p_fSt->group_vs_chef_stat[n] = -1;
    }

    //create or clean content of error's file
    f_errors = fopen("errors.txt", "a");
    fprintf(f_errors, "\n\n\n");
    fclose(f_errors);


    FILE *fic;                                                                                      /* file descriptor */

    fic = openLog(nFic,"w");

    /* title line + blank line */

    fprintf (fic, "%31cRestaurant - Description of the internal state\n\n", ' ');
    printHeader(fic, p_fSt);

    closeLog(fic);
}

/**
 *  \brief Writing the present full state as a single line at the end of the file.
 *
 *  If <tt>nFic</tt> is a null pointer or a null string, the lines are written to stdout
 *
 *  The following layout is obeyed for the full state in a single line
 *    \li chef state
 *    \li waiter state 
 *    \li receptioninst state 
 *    \li groups state 
 *    \li table assigned to each group
 *
 *  \param nFic name of the logging file
 *  \param p_fSt pointer to the location where the full internal state of the problem is stored
 */
void saveState (char nFic[], FULL_STAT *p_fSt)
{
    FILE *fic;                                                                                      /* file descriptor */

    fic = openLog(nFic,"a");

    /********************************VERIFICATIONS*******************************/
    f_errors = fopen("errors.txt", "a");

    //first verifications -> verify states
    if(!p_fSt->error_chef_state && 
    (p_fSt->st.chefStat < WAIT_FOR_ORDER || p_fSt->st.chefStat > REST)){
        fprintf(f_errors, "Error on chef's state!\n");
        p_fSt->error_chef_state = 1;
    }
    if(!p_fSt->error_waiter_state && 
    (p_fSt->st.waiterStat < WAIT_FOR_REQUEST || p_fSt->st.waiterStat > TAKE_TO_TABLE)){
        fprintf(f_errors, "Error on waiter's state!\n");
        p_fSt->error_waiter_state = 1;
    }
    if(!p_fSt->error_receptionist_state && 
    (p_fSt->st.receptionistStat < WAIT_FOR_REQUEST || p_fSt->st.receptionistStat > RECVPAY)){
        fprintf(f_errors, "Error on receptionist's state!\n");
        p_fSt->error_receptionist_state = 1;
    }
    if(!p_fSt->error_groups_waiting && 
    (p_fSt->groupsWaiting < 0 || p_fSt->groupsWaiting > p_fSt->nGroups)){
        fprintf(f_errors, "Error on number of groups waiting!\n");
        p_fSt->error_groups_waiting = 1;
    }
    for(int n = 0; n < p_fSt->nGroups; n++){
        if(!p_fSt->error_group_state && 
        (p_fSt->st.groupStat[n] < GOTOREST || p_fSt->st.groupStat[n] > LEAVING)){
            fprintf(f_errors, "Error on group's state!\n");
            p_fSt->error_group_state = 1;
        }
        if(!p_fSt->error_assigned_table && 
        (p_fSt->assignedTable[n] < -1 || p_fSt->assignedTable[n] > 1)){
            fprintf(f_errors, "Error on number of tables!\n");
            p_fSt->error_assigned_table = 1;
        }
    }

    //second verifications -> verify continuity in groups states and groups waiting
    if(!p_fSt->error_groups_waiting_cont && p_fSt->prev_groups_waiting != -1 &&
    (p_fSt->groupsWaiting > (p_fSt->prev_groups_waiting + 1) || 
    p_fSt->groupsWaiting < (p_fSt->prev_groups_waiting - 1))){
        fprintf(f_errors, "Error on continuity of groups waiting!\n");
        p_fSt->error_groups_waiting_cont = 1;
    }
    
    for(int n = 0; n < p_fSt->nGroups; n++)
        if(!p_fSt->error_group_state_cont && p_fSt->prev_group_stat[n] != -1 &&
        (p_fSt->st.groupStat[n] > (p_fSt->prev_group_stat[n] + 1) || 
        p_fSt->st.groupStat[n] < p_fSt->prev_group_stat[n])){
            fprintf(f_errors, "Error on continuity of group's state!\n");
            p_fSt->error_group_state_cont = 1;
        }

    //third verifications -> verify if one table is assigned to more than one group
    for(int n = 0, num_assigned_0 = 0, num_assigned_1 = 0; n < p_fSt->nGroups; n++){
        if(p_fSt->assignedTable[n] == 0)
            num_assigned_0++;
        if(p_fSt->assignedTable[n] == 1)
            num_assigned_1++;
        if(!p_fSt->error_assigned_table_number && 
        (num_assigned_0 > 1 || num_assigned_1 > 1)){
            fprintf(f_errors, "Error on number of assigned tables!\n");
            p_fSt->error_assigned_table_number = 1;
        }
    }

    //fourth verification -> verify if entities are acting accordingly
    for(int n = 0; n < p_fSt->nGroups; n++){
        //verify if group calls receptionist to request a table
        if(p_fSt->st.groupStat[n] == ATRECEPTION && 
        p_fSt->st.receptionistStat == ASSIGNTABLE)
            p_fSt->group_vs_receptionist_stat[n] = ASSIGNTABLE;

        if(!p_fSt->error_group_vs_receptionist_stat && 
        (p_fSt->st.groupStat[n] == FOOD_REQUEST && p_fSt->group_vs_receptionist_stat[n] != ASSIGNTABLE)){
            fprintf(f_errors, "Error on agreement between groups and receptionist's states!\n");
            p_fSt->error_group_vs_receptionist_stat = 1;
        }


        //verify if group calls receptionist to request bill
        if(p_fSt->st.groupStat[n] == CHECKOUT &&
        p_fSt->st.receptionistStat == RECVPAY)
            p_fSt->group_vs_receptionist_stat[n] = RECVPAY;

        if(!p_fSt->error_group_vs_receptionist_stat && 
        (p_fSt->st.groupStat[n] == LEAVING && p_fSt->group_vs_receptionist_stat[n] != RECVPAY)){
            fprintf(f_errors, "Error on agreement between groups and receptionist's states!\n");
            p_fSt->error_group_vs_receptionist_stat = 1;
        }


        //verify if group calls waiter to request food
        if(p_fSt->st.groupStat[n] == FOOD_REQUEST &&
        p_fSt->st.waiterStat == INFORM_CHEF)
            p_fSt->group_vs_waiter_stat[n] = INFORM_CHEF;

        if(!p_fSt->error_group_vs_waiter_stat && p_fSt->group_vs_waiter_stat[n] != TAKE_TO_TABLE &&
        (p_fSt->st.groupStat[n] == WAIT_FOR_FOOD && p_fSt->group_vs_waiter_stat[n] != INFORM_CHEF)){
            fprintf(f_errors, "Error on agreement between groups and waiter's states!\n");
            p_fSt->error_group_vs_waiter_stat = 1;
        }


        //verify if waiter takes food to group before it starts to eat
        if(p_fSt->st.groupStat[n] == WAIT_FOR_FOOD &&
        p_fSt->st.waiterStat == TAKE_TO_TABLE)
            p_fSt->group_vs_waiter_stat[n] = TAKE_TO_TABLE;

        if(!p_fSt->error_group_vs_waiter_stat && 
        (p_fSt->st.groupStat[n] == EAT && p_fSt->group_vs_waiter_stat[n] != TAKE_TO_TABLE)){
            fprintf(f_errors, "Error on agreement between groups and waiter's states!\n");
            p_fSt->error_group_vs_waiter_stat = 1;
        }


        //verify if waiter takes food to group before it starts to eat
        if((p_fSt->st.groupStat[n] == FOOD_REQUEST || p_fSt->st.groupStat[n] == WAIT_FOR_FOOD) &&
        p_fSt->st.chefStat == COOK)
            p_fSt->group_vs_chef_stat[n] = COOK;

        if(!p_fSt->error_group_vs_chef_stat && 
        (p_fSt->st.groupStat[n] == EAT && p_fSt->group_vs_chef_stat[n] != COOK)){
            fprintf(f_errors, "Error on agreement between groups and chef's states!\n");
            p_fSt->error_group_vs_chef_stat = 1;
        }
    }


    //update value of prevs params
    p_fSt->prev_chef_stat = p_fSt->st.chefStat;
    p_fSt->prev_waiter_stat = p_fSt->st.waiterStat;
    p_fSt->prev_receptionist_stat = p_fSt->st.receptionistStat;
    p_fSt->prev_groups_waiting = p_fSt->groupsWaiting;
    for(int n = 0; n < p_fSt->nGroups; n++){
        p_fSt->prev_group_stat[n] = p_fSt->st.groupStat[n];
        p_fSt->prev_assigned_table[n] = p_fSt->assignedTable[n];
    }

    fclose(f_errors);



    fprintf(fic,"%3d",p_fSt->st.chefStat);
    fprintf(fic,"%3d",p_fSt->st.waiterStat);
    fprintf(fic,"%3d",p_fSt->st.receptionistStat);
    fprintf(fic," ");
    int g;
    for(g=0; g < p_fSt->nGroups; g++) {
        fprintf(fic,"%4d",p_fSt->st.groupStat[g]);
    }

    fprintf(fic,"%5d",p_fSt->groupsWaiting);

    for(g=0; g < p_fSt->nGroups; g++) {
        if(p_fSt->assignedTable[g]!=-1)
            fprintf(fic,"%4d",p_fSt->assignedTable[g]);
        else {
            fprintf(fic,"%4s",".");
        }
    }


    fprintf(fic,"\n");

    closeLog(fic);
}

