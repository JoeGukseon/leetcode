class Solution(object):
    def letterCombinations(self, digits):
        """
        :type digits: str
        :rtype: List[str]
        """
        if not digits:
            return []

        digit_mapping = {'2': 'abc', '3': 'def', '4': 'ghi', '5': 'jkl',
                        '6': 'mno', '7': 'pqrs', '8': 'tuv', '9': 'wxyz'}

        result = []

        def generate_combinations(current, next_digits):
            if not next_digits:
                result.append(current)
                return

            for char in digit_mapping[next_digits[0]]:
                generate_combinations(current + char, next_digits[1:])

        generate_combinations('', digits)
        return result