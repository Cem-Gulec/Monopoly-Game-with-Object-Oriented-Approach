import math
import numpy as np

from sklearn.feature_extraction.text import CountVectorizer


class DataSetMiner(object):
    def __init__(self, documents):
        self.__documents = documents
        self.__data_set_info = self.__set_data_set_info__()
    '''
    This method sets the __data_set_info dictionary which contains values of tf and tf-idf values of given data set.
    '''
    def __set_data_set_info__(self):
        # print(documents)
        vectorizer = CountVectorizer()
        x = vectorizer.fit_transform(self.__documents)

        cols = vectorizer.get_feature_names()
        tf = x.toarray()

        new_list = [tf[i] / len(self.__documents[i]) for i in range(len(self.__documents))]
        some_dict = {}
        a = np.array(new_list)
        for i in range(len(cols)):
            max_tf = max(a[:, i])
            word_name = cols[i]
            appeared = 0
            for j in range(len(self.__documents)):
                if a[j, i] > 0: appeared += 1

            idf = math.log10(len(self.__documents) / appeared)
            some_dict[i] = {
                'term_name': word_name,
                'max_tf': max_tf,
                'appeared': appeared,
                'tf_idf': max_tf * idf,
                'tf_list': a[:, i],
                'term_occurrence_list': tf[:, i],
                'total_term_count': len(cols)
            }
        return some_dict
    '''
        Getter for __data_set_info
    '''
    def get_data_set_info(self):
        return self.__data_set_info

    '''
        This and below methods creaets dictionaries which wil be passed to WordClouder's make_world_cloud method
    '''
    def get_occurrence_tf(self):
        result_dict = {}
        for word_info in self.__data_set_info.values():
            result_dict[word_info["term_name"]] = sum(word_info["term_occurrence_list"]) / word_info["total_term_count"]
        return result_dict

    def get_occurrence_tf_idf(self):
        result_dict = {}
        for word_info in self.__data_set_info.values():
            result_dict[word_info["term_name"]] = (sum(word_info["term_occurrence_list"]) / word_info[
                "total_term_count"]) * word_info["tf_idf"]
        return result_dict

    def get_tf(self):
        result_dict = {}
        for word_info in self.__data_set_info.values():
            result_dict[word_info["term_name"]] = word_info["max_tf"]
        return result_dict

    def get_tf_idf(self):
        result_dict = {}
        for word_info in self.__data_set_info.values():
            result_dict[word_info["term_name"]] = word_info["max_tf"] * word_info["tf_idf"]
        return result_dict
