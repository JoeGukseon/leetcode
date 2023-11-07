/**
 * Definition for a Node.
 * struct Node {
 *     int val;
 *     struct Node *left;
 *     struct Node *right;
 *     struct Node *next;
 * };
 */

struct Node* connect(struct Node* root) {
	if (root == NULL) {
        return NULL;
    }

    struct Node* queue[10000];  // 큐를 배열로 구현
    int front = 0, rear = 0;
    queue[rear++] = root;

    while (front < rear) {
        int levelSize = rear - front;

        for (int i = 0; i < levelSize; i++) {
            struct Node* current = queue[front++];

            if (i < levelSize - 1) {
                current->next = queue[front];
            }

            if (current->left != NULL) {
                queue[rear++] = current->left;
            }

            if (current->right != NULL) {
                queue[rear++] = current->right;
            }
        }
    }

    return root;
}