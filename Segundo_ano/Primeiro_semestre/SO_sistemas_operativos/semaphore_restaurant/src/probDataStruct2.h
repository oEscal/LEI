/**
 *  \file probDataStruct.h (interface file)
 *
 *  \brief Problem name: Restaurant
 *
 *  Definition of internal data structures.
 *
 *  They specify internal metadata about the status of the intervening entities.
 *
 *  \author Nuno Lau - December 2018
 */

#ifndef PROBDATASTRUCT_H_
#define PROBDATASTRUCT_H_

#include <stdbool.h>

#include "probConst.h"

/**
 *  \brief Definition of requests to receptionist and waiter 
 */
typedef struct {
    /** \brief request id */
    int reqType;
    /** \brief group that issues the request */
    int reqGroup;
} request;


/**
 *  \brief Definition of <em>state of the intervening entities</em> data type.
 */
typedef struct {
    /** \brief receptionist state */
    unsigned int receptionistStat;
    /** \brief waiter state */
    unsigned int waiterStat;
    /** \brief chef state */
    unsigned int chefStat;
    /** \brief group state array */
    unsigned int groupStat[MAXGROUPS];

} STAT;


/**
 *  \brief Definition of <em>full state of the problem</em> data type.
 */
typedef struct
{   /** \brief state of all intervening entities */
    STAT st;

    /** \brief number of groups */
    int nGroups;
    /** \brief number of groups waiting for table */
    int groupsWaiting;

    /** \brief estimated start time of groups */
    int startTime[MAXGROUPS];
    /** \brief estimated eat time of groups */
    int eatTime[MAXGROUPS];

    /** \brief saves the table that is being used by each group */
    int assignedTable[MAXGROUPS];

    /** \brief flag of food request from waiter to chef */
    int foodOrder;
    /** \brief group associated to food request from waiter to chef */
    int foodGroup;


    /** \brief used by groups to store request to receptionist */
    request receptionistRequest;

    /** \brief used by groups and chef to store request to waiter */
    request waiterRequest;


    /*************************FOR ERRORS HANDLING*************************/
    //params that save privious state
    int prev_chef_stat;
    int prev_waiter_stat;
    int prev_receptionist_stat;
    int prev_group_stat[MAXGROUPS];
    int prev_groups_waiting;
    int prev_assigned_table[MAXGROUPS];
    int group_vs_receptionist_stat[MAXGROUPS];
    int group_vs_waiter_stat[MAXGROUPS];
    int group_vs_chef_stat[MAXGROUPS];

    //create initialize params that save if a error was previously triggered
    int error_chef_state;
    int error_waiter_state;
    int error_receptionist_state;
    int error_group_state;
    int error_groups_waiting;
    int error_assigned_table;
    int error_groups_waiting_cont;
    int error_group_state_cont;
    int error_assigned_table_number;
    int error_group_vs_receptionist_stat;
    int error_group_vs_waiter_stat;
    int error_group_vs_chef_stat;
    /*******************************END***********************************/

} FULL_STAT;


#endif /* PROBDATASTRUCT_H_ */
