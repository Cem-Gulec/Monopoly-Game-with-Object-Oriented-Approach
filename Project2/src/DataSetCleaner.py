import os
from nltk.corpus import stopwords
from nltk.tokenize import word_tokenize
from pdfminer.pdfinterp import PDFResourceManager, PDFPageInterpreter
from pdfminer.converter import TextConverter
from pdfminer.layout import LAParams
from pdfminer.pdfpage import PDFPage
from io import StringIO


class DataSetCleaner(object):

    def __init__(self):
        self.stop_words = self.prepare_stop_words()

    '''
        This method iterates over /resources folder and and clears each document.
        Returns list which contains each document.
    '''
    def get_term_list(self):
        dir_path = os.path.dirname(os.path.realpath(__file__))
        resourcesPath = dir_path[0:-3] + "/resources"
        k = 0
        cleared_article_text_list = []


        for filename in os.listdir(resourcesPath):
            k += 1
            if filename.endswith(".pdf"):
                pdfPath = os.path.join(resourcesPath, filename)
                article = self.convert_pdf_to_text(pdfPath)
                cleared_article_text = self.clearArticleText(article)
                cleared_article_text_list.append(cleared_article_text)


                continue
            else:

                continue

        return cleared_article_text_list
    '''
        This method takes .pdf and converts it to python string.
    '''
    def convert_pdf_to_text(self, path_to_pdf):
        rsrcmgr = PDFResourceManager()
        retstr = StringIO()
        codec = 'utf-8'
        laparams = LAParams()
        device = TextConverter(rsrcmgr, retstr, codec=codec, laparams=laparams)
        # readyPath = os.path.join(os.path.dirname(__file__),  pathToPdf)
        fp = open(path_to_pdf, 'rb')
        interpreter = PDFPageInterpreter(rsrcmgr, device)
        password = ""
        maxpages = 0
        caching = True
        pagenos = set()

        for page in PDFPage.get_pages(fp, pagenos, maxpages=maxpages, password=password, caching=caching,
                                      check_extractable=True):
            interpreter.process_page(page)

        text = retstr.getvalue()
        # print(text)
        fp.close()
        device.close()
        retstr.close()
        return text

    '''
        This method checks each word in each document and clears it by using nltk's stop words and additional stop words
        which we provided
    '''
    def clearArticleText(self, article_text):
        word_tokens = word_tokenize(article_text.lower())
        filtered_article_word_list = [w for w in word_tokens if w not in self.stop_words and w.isalpha()]
        cleared_article_text = " ".join(filtered_article_word_list)
        return cleared_article_text

    '''
        This method adds additional stop words to nltk's stop words. 
    '''
    def prepare_stop_words(self):
        stop_words = stopwords.words('english')
        with open(os.getcwd() + "/additionalStopWords.txt", 'rb') as open_file_object:
            for line in open_file_object:
                line = line.decode().strip()
                stop_words.append(line)
        return stop_words











