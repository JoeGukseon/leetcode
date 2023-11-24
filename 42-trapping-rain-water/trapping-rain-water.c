int trap(int* height, int heightSize) {
    int left = 0, right = heightSize - 1;
    int left_max = 0, right_max = 0;
    int result = 0;

    while (left < right) {
        left_max = height[left] > left_max ? height[left] : left_max;
        right_max = height[right] > right_max ? height[right] : right_max;

        if (height[left] < height[right]) {
            result += left_max - height[left];
            left++;
        } else {
            result += right_max - height[right];
            right--;
        }
    }

    return result;
}