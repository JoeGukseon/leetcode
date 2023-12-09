char* addBinary(char* a, char* b) {
    int lenA = strlen(a);
    int lenB = strlen(b);
    int maxLen = lenA > lenB ? lenA : lenB;

    char* result = (char*)malloc((maxLen + 2) * sizeof(char));
    int carry = 0;

    int i = lenA - 1;
    int j = lenB - 1;
    int k = 0;

    while (i >= 0 || j >= 0 || carry > 0) {
        int numA = (i >= 0) ? a[i] - '0' : 0;
        int numB = (j >= 0) ? b[j] - '0' : 0;

        int _sum = numA + numB + carry;

        result[k++] = _sum % 2 + '0';
        carry = _sum / 2;

        i--;
        j--;
    }

    result[k] = '\0';
    for (int start = 0, end = k - 1; start < end; start++, end--) {
        char temp = result[start];
        result[start] = result[end];
        result[end] = temp;
    }

    return result;
}