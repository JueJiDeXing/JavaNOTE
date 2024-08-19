@echo off
for /r %%f in (*) do (
    if not "%%~xf"==".bat" (
        ren "%%f" "%%~nf.sql"
    )
)
