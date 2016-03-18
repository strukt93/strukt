import re

url = "javascript:alert(1)"
if str(url).startswith('javascript'):
    print(1)