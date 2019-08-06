#importing libraries
from bs4 import BeautifulSoup as bs 
from selenium import webdriver
import requests

#defining driver path
driver = webdriver.Chrome(executable_path="C:/Users/cemgg/Anaconda3/chromedriver")
driver.implicitly_wait(30) 

#defining main url and transmit it into the driver
url = "https://muratcanganiz.com/" 
driver.get(url)
soup = bs(driver.page_source, "lxml")

#getting every div with defined classs name: col-xs-12 col-md-8
divs = soup.findAll("div", {"class": "col-xs-12 col-md-8"})

#defining lists for later use
pdfUrls = []
pdfNames = []

#reaching every div a and its href attribution
#if the last 3 character is pdf then add those urls to the lists
#after getting urls, put every character except the root characters into the "pdfNames" list
#example pdfUrls element: https://muratcanganiz.com/publications/UBMK2017_OracleFailurePrediction_v3.pdf
#example pdfNames element: UBMK2017_OracleFailurePrediction_v3.pdf
for ext in soup.find_all('a'):
     length = len(ext.get('href'))
     if ext.get('href')[length-3:length].lower() == "pdf":
        pdfUrls.append(url + ext.get('href'))
        href = ext.get('href')
        pdfNames.append(href[13:])

#just to print elements
for i in range(1,27):
    print(pdfUrls[i])
    print(pdfNames[i] + '\n')

#to prevent errors replace each space in the pdfUrls elements with %20
pdfUrls = [i.replace(' ', '%20') for i in pdfUrls]
for i in range(1,27):
    print(pdfUrls[i])

#this code block allows us to request the belonging link then write it as requested extension format
for i in range(1,25):
    url = pdfUrls[i]  
    r = requests.get(url)

    with open(pdfNames[i], 'wb') as f:  
        f.write(r.content)

