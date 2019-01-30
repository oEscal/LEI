/**
 *  \file semSharedReceptionist.c (implementation file)
 *
 *  \brief Problem name: Restaurant
 *
 *  Synchronization based on semaphores and shared memory.
 *  Implementation with SVIPC.
 *
 *  Definition of the operations carried out by the receptionist:
 *     \li waitForGroup
 *     \li provideTableOrWaitingRoom
 *     \li receivePayment
 *
 *  \author Nuno Lau - December 2018
 */

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <unistd.h>
#include <sys/types.h>
#include <string.h>
#include <math.h>
#include <assert.h>

#include "probConst.h"
#include "probDataStruct.h"
#include "logging.h"
#include "sharedDataSync.h"
#include "semaphore.h"
#include "sharedMemory.h"
#include "handle_semaphore_access.h"

/** \brief logging file name */
static char nFic[51];

/** \brief shared memory block access identifier */
static int shmid;

/** \brief semaphore set access identifier */
static int semgid;

/** \brief pointer to shared memory region */
static SHARED_DATA *sh;

/* constants for groupRecord */
#define TOARRIVE 0
#define WAIT     1
#define ATTABLE  2
#define DONE     3

/** \brief receptioninst view on each group evolution (useful to decide table binding) */
static int groupRecord[MAXGROUPS];


/** \brief receptionist waits for next request */
static request waitForGroup ();

/** \brief receptionist waits for next request */
static void provideTableOrWaitingRoom (int n);

/** \brief receptionist receives payment */
static void receivePayment (int n);



/**
 *  \brief Main program.
 *
 *  Its role is to generate the life cycle of one of intervening entities in the problem: the receptionist.
 */
int main (int argc, char *argv[])
{
    int key;                                            /*access key to shared memory and semaphore set */
    char *tinp;                                                       /* numerical parameters test flag */

    /* validation of command line parameters */
    if (argc != 4) { 
        freopen ("error_RT", "a", stderr);
        fprintf (stderr, "Number of parameters is incorrect!\n");
        return EXIT_FAILURE;
    }
    else { 
        freopen (argv[3], "w", stderr);
        setbuf(stderr,NULL);
    }

    strcpy (nFic, argv[1]);
    key = (unsigned int) strtol (argv[2], &tinp, 0);
    if (*tinp != '\0') {   
        fprintf (stderr, "Error on the access key communication!\n");
        return EXIT_FAILURE;
    }

    /* connection to the semaphore set and the shared memory region and mapping the shared region onto the
       process address space */
    if ((semgid = semConnect (key)) == -1) { 
        perror ("error on connecting to the semaphore set");
        return EXIT_FAILURE;
    }
    if ((shmid = shmemConnect (key)) == -1) { 
        perror ("error on connecting to the shared memory region");
        return EXIT_FAILURE;
    }
    if (shmemAttach (shmid, (void **) &sh) == -1) { 
        perror ("error on mapping the shared region on the process address space");
        return EXIT_FAILURE;
    }

    /* initialize random generator */
    srandom ((unsigned int) getpid ());              

    /* initialize internal receptionist memory */
    int g;
    for (g=0; g < sh->fSt.nGroups; g++) {
       groupRecord[g] = TOARRIVE;
    }

    /* simulation of the life cycle of the receptionist */
    int nReq=0;
    request req;
    while( nReq < sh->fSt.nGroups*2 ) {
        req = waitForGroup();
        switch(req.reqType) {
            case TABLEREQ:
                   provideTableOrWaitingRoom(req.reqGroup); //TODO param should be groupid
                   break;
            case BILLREQ:
                   receivePayment(req.reqGroup);
                   break;
        }
        nReq++;
    }

    /* unmapping the shared region off the process address space */
    if (shmemDettach (sh) == -1) {
        perror ("error on unmapping the shared region off the process address space");
        return EXIT_FAILURE;;
    }

    return EXIT_SUCCESS;
}

/**
 *  \brief decides table to occupy for group n or if it must wait.
 *
 *  Checks current state of tables and groups in order to decide table or wait.
 *
 *  \return table id or -1 (in case of wait decision)
 */
static int decideTableOrWait(int n)
{
    //TODO insert your code here
    if(sh->fSt.st.groupStat[n] == ATRECEPTION && sh->fSt.assignedTable[n] == -1){
        int occupied = 0;
        for(int num = 0; num < sh->fSt.nGroups; num++)
            if(sh->fSt.assignedTable[num] != -1)
                occupied += 1 + sh->fSt.assignedTable[num];

        if(occupied <= 1)
            return 1;
        if(occupied == 2)
            return 0;
    }

    return -1;
}

/**
 *  \brief called when a table gets vacant and there are waiting groups 
 *         to decide which group (if any) should occupy it.
 *
 *  Checks current state of tables and groups in order to decide group.
 *
 *  \return group id or -1 (in case of wait decision)
 */
static int decideNextGroup()
{
     //TODO insert your code here
     for(int num = 0; num < sh->fSt.nGroups; num++)
        if(decideTableOrWait(num) != -1 && groupRecord[num] == WAIT)
            return num;

     return -1;
}

/**
 *  \brief receptionist waits for next request 
 *
 *  Receptionist updates state and waits for request from group, then reads request,
 *  and signals availability for new request.
 *  The internal state should be saved.
 *
 *  \return request submitted by group
 */
static request waitForGroup()
{
    request ret; 

    if (semDown (semgid, sh->mutex) == -1)  {                                                  /* enter critical region */
        perror ("error on the up operation for semaphore access (WT)");
        exit (EXIT_FAILURE);
    }

    // TODO insert your code here
    sh->fSt.st.receptionistStat = WAIT_FOR_REQUEST;
    saveState(nFic, &sh->fSt);
    
    if (semUp (semgid, sh->mutex) == -1)      {                                             /* exit critical region */
        perror ("error on the down operation for semaphore access (WT)");
        exit (EXIT_FAILURE);
    }


    // TODO insert your code here
    verifySemError(semDown(semgid, sh->receptionistReq), 1, 3);
    if (semDown (semgid, sh->mutex) == -1)  {                                                  /* enter critical region */
        perror ("error on the up operation for semaphore access (WT)");
        exit (EXIT_FAILURE);
    }

    // TODO insert your code here
    ret.reqGroup = sh->fSt.receptionistRequest.reqGroup;
    ret.reqType = sh->fSt.receptionistRequest.reqType;

    verifySemError(semUp(semgid, sh->receptionistRequestPossible), 0, 3);

    if (semUp (semgid, sh->mutex) == -1) {                                                  /* exit critical region */
        perror ("error on the down operation for semaphore access (WT)");
        exit (EXIT_FAILURE);
    }
    // TODO insert your code here
    return ret;

}

/**
 *  \brief receptionist decides if group should occupy table or wait
 *
 *  Receptionist updates state and then decides if group occupies table
 *  or waits. Shared (and internal) memory may need to be updated.
 *  If group occupies table, it must be informed that it may proceed. 
 *  The internal state should be saved.
 *
 */
static void provideTableOrWaitingRoom (int n)
{

    if (semDown (semgid, sh->mutex) == -1)  {                                                  /* enter critical region */
        perror ("error on the up operation for semaphore access (WT)");
        exit (EXIT_FAILURE);
    }

    // TODO insert your code here
    sh->fSt.st.receptionistStat = ASSIGNTABLE;
    saveState(nFic, &sh->fSt);

    if(groupRecord[n] == TOARRIVE){
        if((sh->fSt.assignedTable[n] = decideTableOrWait(n)) != -1){
            verifySemError(semUp(semgid, sh->waitForTable[n]), 0, 3);
            groupRecord[n] = ATTABLE;
        }else{
            groupRecord[n] = WAIT;
            sh->fSt.groupsWaiting++;
        }
    }

    if (semUp (semgid, sh->mutex) == -1) {                                               /* exit critical region */
        perror ("error on the down operation for semaphore access (WT)");
        exit (EXIT_FAILURE);
    }

}

/**
 *  \brief receptionist receives payment 
 *
 *  Receptionist updates its state and receives payment.
 *  If there are waiting groups, receptionist should check if table that just became
 *  vacant should be occupied. Shared (and internal) memory should be updated.
 *  The internal state should be saved.
 *
 */

static void receivePayment (int n)
{
    if (semDown (semgid, sh->mutex) == -1)  {                                                  /* enter critical region */
        perror ("error on the up operation for semaphore access (WT)");
        exit (EXIT_FAILURE);
    }

    // TODO insert your code here
    int table_vacant;
    int new_table_group;

    sh->fSt.st.receptionistStat = RECVPAY;
    saveState(nFic, &sh->fSt);

    verifySemError(semUp(semgid, sh->tableDone[table_vacant = sh->fSt.assignedTable[n]]), 0, 3);
    groupRecord[n] = DONE;
    sh->fSt.assignedTable[n] = -1;

    if(sh->fSt.groupsWaiting > 0){
        sh->fSt.st.receptionistStat = ASSIGNTABLE;
        saveState(nFic, &sh->fSt);

        if((new_table_group = decideNextGroup()) != -1){
            sh->fSt.assignedTable[new_table_group] = table_vacant;
            groupRecord[n] = ATTABLE;
            verifySemError(semUp(semgid, sh->waitForTable[new_table_group]), 0, 3);

            sh->fSt.groupsWaiting--;
        }
    }

    if (semUp (semgid, sh->mutex) == -1)  {                                                  /* exit critical region */
        perror ("error on the down operation for semaphore access (WT)");
        exit (EXIT_FAILURE);
    }

    // TODO insert your code here
}

