uint32_t reverseBits(uint32_t n) {
    uint32_t result = 0;
    
    for (int i = 0; i < 32; i++) {
        int bit = (n >> i) & 1;
        result = (result << 1) | bit;
    }
    
    return result;
}