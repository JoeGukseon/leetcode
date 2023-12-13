/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     struct TreeNode *left;
 *     struct TreeNode *right;
 * };
 */

struct MaxPathSum {
    int maxSum;
};

struct TreeNode* createNode(int val) {
    struct TreeNode* newNode = (struct TreeNode*)malloc(sizeof(struct TreeNode));
    newNode->val = val;
    newNode->left = NULL;
    newNode->right = NULL;
    return newNode;
}

struct MaxPathSum* createMaxPathSum() {
    struct MaxPathSum* maxPathSum = (struct MaxPathSum*)malloc(sizeof(struct MaxPathSum));
    maxPathSum->maxSum = INT_MIN;
    return maxPathSum;
}

int max(int a, int b) {
    return (a > b) ? a : b;
}

int calculateMaxPathSum(struct TreeNode* node, struct MaxPathSum* maxPathSum) {
    if (!node) {
        return 0;
    }

    int leftMax = max(0, calculateMaxPathSum(node->left, maxPathSum));

    int rightMax = max(0, calculateMaxPathSum(node->right, maxPathSum));

    int currentMax = node->val + leftMax + rightMax;

    maxPathSum->maxSum = max(maxPathSum->maxSum, currentMax);

    return node->val + max(leftMax, rightMax);
}

int maxPathSum(struct TreeNode* root) {
    struct MaxPathSum* maxPathSum = createMaxPathSum();
    calculateMaxPathSum(root, maxPathSum);
    return maxPathSum->maxSum;
}