import requests
import re
from bs4 import BeautifulSoup


def iterate_links(response):
    for link in response.find_all('a'):
        href = link.get('href')
        prepare_url(href)


def make_request(url):
    result = requests.get(url).text
    soup = BeautifulSoup(result, "html.parser")
    iterate_links(soup)


def check_list(url):
    new = url
    if url.endswith('/'):
        new = url[:len(url) - 1]
    if new not in links:
        print(new)
        links.append(new)
        make_request(new)


def prepare_url(url):
    if str(url).startswith('#') or str(url).startswith('javascript'):
        return
    exp = re.search(r'^((http|https)://(.*)\.(.*)(\.(.*)))*', str(url))
    if not(exp.group(0) == ''):
        check_list(url)
    else:
        exp = re.search(r'(\.\./)+(.)*', str(url))
        if not(exp is None):
            return
        else:
            exp = re.search(r'^[^/](.*)\.(.*)(\.(.*))*', str(url))
            if not(exp is None):
                check_list('http://' + url)

url = input("Enter a URL:  ")
links = []
base_url = url
prepare_url(url)
