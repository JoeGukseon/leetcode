#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_SIZE 10

typedef struct {
    char gene[MAX_SIZE];
    int mutations;
} GeneMutationNode;

void freeGeneBank(char** geneBank, int bankSize) {
    for (int i = 0; i < bankSize; i++) {
        free(geneBank[i]);
    }
    free(geneBank);
}

int minMutation(char* startGene, char* endGene, char** bank, int bankSize) {
    char** geneBank = (char**)malloc(sizeof(char*) * bankSize);
    for (int i = 0; i < bankSize; i++) {
        geneBank[i] = (char*)malloc(sizeof(char) * (strlen(bank[i]) + 1));
        strcpy(geneBank[i], bank[i]);
    }

    bool isFound = false;
    for (int i = 0; i < bankSize; i++) {
        if (strcmp(endGene, geneBank[i]) == 0) {
            isFound = true;
            break;
        }
    }

    if (!isFound) {
        freeGeneBank(geneBank, bankSize);
        return -1;
    }

    GeneMutationNode* queue = (GeneMutationNode*)malloc(sizeof(GeneMutationNode) * (bankSize + 1));
    int front = 0, rear = 0;

    //큐에 넣기
    strcpy(queue[rear].gene, startGene);
    queue[rear].mutations = 0;
    rear++;

    while (front < rear) {
        int size = rear - front;

        for (int i = 0; i < size; i++) {
            char* currentGene = queue[front].gene;
            int mutations = queue[front].mutations;
            front++;

            for (int j = 0; j < strlen(currentGene); j++) {
                char* geneArray = strdup(currentGene);

                const char* nucleotides = "ACGT";
                for (int c = 0; c < 4; c++) {
                    geneArray[j] = nucleotides[c];
                    char* mutatedGene = strdup(geneArray);

                    if (strcmp(mutatedGene, endGene) == 0) {
                        freeGeneBank(geneBank, bankSize);
                        free(queue);
                        return mutations + 1;
                    }

                    for (int k = 0; k < bankSize; k++) {
                        if (geneBank[k] != NULL && strcmp(mutatedGene, geneBank[k]) == 0) {
                            free(geneBank[k]);
                            geneBank[k] = NULL;
                            strcpy(queue[rear].gene, mutatedGene);
                            queue[rear].mutations = mutations + 1;
                            rear++;
                        }
                    }
                    free(mutatedGene);
                }
                free(geneArray);
            }
        }
    }

    freeGeneBank(geneBank, bankSize);
    free(queue);
    return -1;
}