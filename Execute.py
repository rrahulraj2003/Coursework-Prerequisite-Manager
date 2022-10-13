import os, time

print( '''
Welcome to Prereqchecker!
-------------------------

Instructions:
Change the adjlist.in contents (of # of vertices, the vertex names and edges)
to match your courses and their prerequisites or keep the Rutgers CS Courses.

Once you're done, press Enter. ''')

# Done
input()


print('''Great, choose an option:
1. See the graph of the vertices and their edges (in adjacent list form).
2. Check to see if a course would be a valid prereq for another course.
3. See what courses you are eligible to take, given your completed courses.
4. Find out what courses you need to complete to be eligible for a selected course.\

''')

# Input
option = int(input())
print("\n")

# Is option an int
if(isinstance(option, int)):

    if(option == 1):    # AdjList
        print("Adjacent List Representation\n----------------------------")
        os.system('cmd /c "java -cp bin prereqchecker.AdjList adjlist.in adjlist.out"')
        file1 = open('src/prereqchecker/adjlist.out', 'r')
        lines = file1.readlines()
        for line in lines:
            print(line.strip())
        print()
        file1.close()

    elif(option == 2):  # Valid Prereq
        print("Valid Prerequisite\n------------------")
        print("Type in the first course on line 1 and the second course on line 2.")
        print("*** The result will show if the 2nd course could be a valid prereq for the 1st course. ***")
        
        print("1.", end = " ")
        first = input()
        print("2.", end = " ")
        second = input()

        file1 = open("src/prereqchecker/validprereq.in","w")
        list = [first + "\n", second]
        file1.writelines(list)
        file1.close()

        os.system('cmd /c "java -cp bin prereqchecker.ValidPrereq adjlist.in validprereq.in validprereq.out"')
        file2 = open('src/prereqchecker/validprereq.out', 'r')
        lines = file2.readlines()
        for line in lines:
            print(line.strip())
        file2.close()

    elif(option == 3):  # Eligible
        print("Eligible Classes\n----------------")
        print("To make this process simpler, go to eligible.in, add the number of classes you have")
        print("taken on the first line and add your completed classes on each line below that.")
        print("Press Enter to see the results after you are done (Make sure to save the file!)")
        
        input()
        
        os.system('cmd /c "java -cp bin prereqchecker.Eligible adjlist.in eligible.in eligible.out"')
        file1 = open('src/prereqchecker/eligible.out', 'r')
        lines = file1.readlines()
        print("Eligible courses:")
        for line in lines:
            print(line.strip())
        print()
        file1.close()

    elif(option == 4):  # NeedToComplete
        print("Need To Complete\n----------------")
        print("To make this process simpler, go to needtocomplete.in, add what course you want to")
        print("complete on the first line, add the number of completed courses on the second line,")
        print("and finally add the completed courses on each lines below that.")
        print("Press Enter to see the results after you are done (Make sure to save the file!)")
        
        input()
        
        os.system('cmd /c "java -cp bin prereqchecker.NeedToComplete adjlist.in needtocomplete.in needtocomplete.out"')
        file1 = open('src/prereqchecker/needtocomplete.out', 'r')
        lines = file1.readlines()
        for line in lines:
            print(line.strip())
        print()
        file1.close()
    else:
        print("Invalid Input. Program terminated.")

else:
    print("Invalid Input. Program terminated.")