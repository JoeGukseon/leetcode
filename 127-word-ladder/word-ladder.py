class Solution(object):
    def ladderLength(self, beginWord, endWord, wordList):
        """
        :type beginWord: str
        :type endWord: str
        :type wordList: List[str]
        :rtype: int
        """
        wordSet = set(wordList)

        if endWord not in wordSet:
            return 0

        queue = deque()
        queue.append(beginWord)

        level = 1

        while queue:
            size = len(queue)

            for i in range(size):
                currentWord = queue.popleft()

                for j in range(len(currentWord)):
                    originChar = currentWord[j]

                    for c in 'abcdefghijklmnopqrstuvwxyz':
                        if c != originChar:
                            newWord = self.replaceChar(currentWord,j,c)

                            if newWord == endWord:
                                return level + 1  

                            if newWord in wordSet:
                                wordSet.remove(newWord)
                                queue.append(newWord)

                    self.restoreChar(currentWord, j, originChar)

            level += 1

        return 0 

    def replaceChar(self, word, index, newChar):
        charList = list(word)
        charList[index] = newChar
        return "".join(charList)

    def restoreChar(self, word, index, originChar):
        charList = list(word)
        charList[index] = originChar
        return "".join(charList)