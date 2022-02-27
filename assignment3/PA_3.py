import os
import time
import argparse

def load_txt(filename):
	""" return a textfile from a given filename
	"""
	with open(filename) as f_stream:
		file = f_stream.read()
	return file

# Naive sliding window
def sliding_window(pat, text):
	""" Naive sliding window
		yields the index of each matching pattern in the text
		pat: patter to search for (String)
		text: text to be searched (String)
	"""
	m = len(pat)
	n = len(text)
	i = 0
	while i <= n - m:
		j = 0
		while j < m and pat[j]==text[i+j]:
			j += 1
		if j==m:
			yield i #returns index of the found match
		i += 1

# Morris-Pratt-Shift
def create_border(pat):
	"""Returns the border table for a given string
	pat: String
	"""
	m = len(pat)
	Bord = [0]*(len(pat)+1)
	Bord[0] = -1
	t = -1
	for j in range(1, m):
		while t >= 0 and pat[t] != pat[j-1]:
			t = Bord[t]
		t += 1
		Bord[j] = t
	return Bord[1:] #ommit the -1

# Knuth-Morris-Pratt-Shift
def strong_border(pat):
	m = len(pat)
	Strong_Bord = [0]*(len(pat)+1)
	Strong_Bord[0] = -1
	t = -1
	for j in range(1, m):
		t = Strong_Bord[j-1]
		while t >= 0 and pat[t] != pat[j-1]:
			t = Strong_Bord[t]
		t += 1
		
		if j==m or pat[t] != pat[j]:
			Strong_Bord[j] = t
		else:
			Strong_Bord[j] = Strong_Bord[t]
	return Strong_Bord[1:] #ommit the -1

def sliding_with_shift(pat, text):
	m = len(pat)
	n = len(text)
	kmp_shift = strong_border(pat)
	i = 0
	j = 0
	while i <= n - m:
		while j < m and pat[j]==text[i+j]:
			j += 1
			
		if j==m:
			yield i
		i += 1 + mp_shift[j-1] #The window is shifted by 1 + the border
		j = max(0, mp_shift[j-1])

# Offline Algorithm
# Simple Search

def return_lcp(str_1, str_2):
	# The maximum lcp can just be the smalest length of both strings
	max_len = min(len(str_1), len(str_2))
	lcp = 0
	
	# If the first comparison fails the seconds is not evaluated,
	# which prefends an out of bounds exception
	while lcp < max_len and str_1[lcp]==str_2[lcp]:
		lcp += 1
	return lcp

def get_lst_suffixes(str_1):
	""" 
	str_1 : String from which to create the suffix list
	"""
	
	# The tilde was used because it is the second last character in the Ascii table
	str_1 = str_1 + '~'
	
	# Start at position -1 and decrease the value by 1 in each iteration,
	# resulting in a list of suffixes
	suff_lst = [str_1[-i:] for i in range(1, len(str_1)+1)]
	
	# Add the empty string and a lexicographically big value and sort the array
	return sorted([''] + suff_lst)
	
def simple_search(x, L):
	"""
	x: Pattern to search for
	L: List of Lexicographically ordered suffixes
	"""
	d = -1
	f = len(L)
	m = len(x)
	while d+1 < f:
		i = int((d+f)/2)
		l = return_lcp(x, L[i])
		if l==m and l==len(L[i]):
			return i
		elif l==len(L[i]) or (l != m and L[i][l] < x[l]):
			d = i
		else:
			f = i
	return d, f
	
if __name__=="__main__":
	#Parse the arguments
	parser = argparse.ArgumentParser()
	parser.add_argument("-f", "--file",
						help="The filename of the text",
						required=True)
	parser.add_argument("-p", "--pattern",
						help="The pattern to look in the text",
						required=True)
	args = parser.parse_args()
	
	#Get the variables
	filename = args.file
	text = load_txt(filename)
	pattern = args.pattern
	
	#Start searching
	
	#Online Algorithms
	#Naive sliding
	print("Naive sliding window")
	start = time.time()
	result = list(sliding_window(pattern, text))
	print(result)
	print("Total time: {:.2f} sec".format(time.time()-start))
	
	# KMP-Algorithm
	print()
	print("KMP-Algorithm")
	start = time.time()
	result = sliding_with_shift(pattern, text)
	print(result)
	print("Total time: {:.2f} sec".format(time.time()-start))
	
	#Offline Algorithm
	#Simple Search
	print()
	print("Offline Algorithm: Simple Search")
	start = time.time()
	
	
	
	
