miasta=[
    [1,2,170],
    [1,3,362],
    [1,6,165],
    [1,7,377],
    [2,1,170],
    [2,7,212],
    [3,1,362],
    [3,5,235],
    [3,4,217],
    [4,3,217],
    [4,5,152],
    [4,8,156],
    [5,4,152],
    [5,3,235],
    [5,7,319],
    [5,9,203],
    [5,8,186],
    [6,1,165],
    [6,9,227],
    [6,7,270],
    [7,1,377],
    [7,2,212],
    [7,6,270],
    [7,5,319],
    [7,9,137],
    [8,4,156],
    [8,5,186],
    [8,9,209],
    [9,7,137],
    [9,6,227],
    [9,5,203],
    [9,8,209]
    ]
i = 0
j=0
t=0
odl = 0
startCity=int(input("podaj miejscowosc od"))
endCity=int(input("podaj miejscowosc do"))
#---------------------------------------------------------------------------------------------------
'''while j < len(miasta):
    if miasta[i][0]==startCity and miasta[i][1]==endCity:
        print(miasta[i][2])
        break
    else:
        i=i+1
    j=j+1'''
#---------------------------------------------------------------------------------------------------
i=0
a=startCity
b=endCity
c=0
z=0
y=0
w=0
def droga(a,b,i,odl):
    while i < len(miasta):
        if miasta[i][0]==a:
            a=miasta[i][1]
            z=miasta[i][0]
            if miasta[i][1]!=z:
                odl=odl+miasta[i][2]
                
                droga(a,b,i,odl)
                
        elif miasta[i][1]==b:
            odl=odl+miasta[i][2]

            return odl
        else:
            i=i+1



            
print(droga(a,b,i,odl))




























'''while i < len(miasta):
    if miasta[i][0]==startCity:
        miasta[i][1]=z
        odl = odl + miasta[i][2]
        while a<len(miasta):
            if miasta[a][0]==z:
                y=miasta[a][1]
                odl = odl + miasta[a][2]
                
                while b < len(miasta):
                    if miasta[b][0]==y:
                        miasta[b][1]=w
                        odl = odl + miasta[b][2]
                    
                        
                        while c<len(miasta):
                            if miasta[c][0]==w:
                                if miasta[c][1]==endCity:
                                    odl = odl + miasta[c][2]
                                    print(odl)
                                                                    
                                  
                            c = c+1
                    elif miasta[a][1]==endCity:
                        odl = odl + miasta[b][2]
                        print(odl)      
                   
                    b = b+1
            elif miasta[a][1]==endCity:
                odl = odl + miasta[a][2]
                print(odl)
                    
            
            a=a+1
    else:
        i=i+1
i=i+1'''
