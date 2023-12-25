int max(int a, int b) {
    return (a > b) ? a : b;
}

int min(int a, int b) {
    return (a < b) ? a : b;
}

double findMedianSortedArrays(int* nums1, int nums1Size, int* nums2, int nums2Size) {
    if (nums1Size > nums2Size) {
        int* temp = nums1;
        nums1 = nums2;
        nums2 = temp;

        int tempSize = nums1Size;
        nums1Size = nums2Size;
        nums2Size = tempSize;
    }

    int low = 0;
    int high = nums1Size;
    int partitionX, partitionY;
    int maxX, minX, maxY, minY;

    while (low <= high) {
        partitionX = (low + high) / 2;
        partitionY = (nums1Size + nums2Size + 1) / 2 - partitionX;

        maxX = (partitionX == 0) ? INT_MIN : nums1[partitionX - 1];
        minX = (partitionX == nums1Size) ? INT_MAX : nums1[partitionX];

        maxY = (partitionY == 0) ? INT_MIN : nums2[partitionY - 1];
        minY = (partitionY == nums2Size) ? INT_MAX : nums2[partitionY];

        if (maxX <= minY && maxY <= minX) {
            if ((nums1Size + nums2Size) % 2 == 0) {
                return (double)(max(maxX, maxY) + min(minX, minY)) / 2;
            } else {
                return max(maxX, maxY);
            }
        } else if (maxX > minY) {
            high = partitionX - 1;
        } else {
            low = partitionX + 1;
        }
    }

    return -1;
}