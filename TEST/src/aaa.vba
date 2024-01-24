Sub CompareCells()
    '设置范围为F2至F10000
    Dim rng As Range
    Set rng = Range("F2:F1000")

    '循环遍历每个单元格
    Dim cell As Range
    For Each cell In rng
        '获取当前单元格所在的行号
        Dim rowNumber As Long
        rowNumber = cell.Row

        '去除空格后进行比较
        Dim cellValue As String
        cellValue = Trim(cell.Value)

        '着色
        If cellValue = "A" Then
            Cells(rowNumber, 2).Interior.ColorIndex = 6
            Cells(rowNumber, 3).Interior.ColorIndex = xlNone
            Cells(rowNumber, 4).Interior.ColorIndex = xlNone
            Cells(rowNumber, 5).Interior.ColorIndex = xlNone
        ElseIf cellValue = "B" Then
            Cells(rowNumber, 2).Interior.ColorIndex = xlNone
            Cells(rowNumber, 3).Interior.ColorIndex = 6
            Cells(rowNumber, 4).Interior.ColorIndex = xlNone
            Cells(rowNumber, 5).Interior.ColorIndex = xlNone
        ElseIf cellValue = "C" Then
            Cells(rowNumber, 2).Interior.ColorIndex = xlNone
            Cells(rowNumber, 3).Interior.ColorIndex = xlNone
            Cells(rowNumber, 4).Interior.ColorIndex = 6
            Cells(rowNumber, 5).Interior.ColorIndex = xlNone
        ElseIf cellValue = "D" Then
            Cells(rowNumber, 2).Interior.ColorIndex = xlNone
            Cells(rowNumber, 3).Interior.ColorIndex = xlNone
            Cells(rowNumber, 4).Interior.ColorIndex = xlNone
            Cells(rowNumber, 5).Interior.ColorIndex = 6
        Else
            Cells(rowNumber, 2).Interior.ColorIndex = xlNone
            Cells(rowNumber, 3).Interior.ColorIndex = xlNone
            Cells(rowNumber, 4).Interior.ColorIndex = xlNone
            Cells(rowNumber, 5).Interior.ColorIndex = xlNone
        End If
    Next cell
End Sub
