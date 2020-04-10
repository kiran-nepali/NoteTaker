package com.example.noteapp.viewmodel.note

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.noteapp.data.Note
import com.example.noteapp.repository.NoteRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class NoteViewModelTest {

    private lateinit var viewModel: NoteViewModel
    private var repository:NoteRepository = mockk()
    private  var notelivedataobserver:Observer<List<Note>> = mockk()
    private var errorobserver:Observer<String> = mockk()

    @JvmField
    @Rule
    val rule:TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = NoteViewModel(repository)
        viewModel.notelivedata.observeForever(notelivedataobserver)
        viewModel.error.observeForever(errorobserver)
    }

    @Test
    fun `when getnotes request is made on db, it returns data`(){
        val listOfnote = listOf(Note("hello", "world"),Note("namaste","hello in nepali"))
        every { repository.getAllNote() } returns Single.just(listOfnote)
        viewModel.getNotes()
        verify(exactly = 1) { notelivedataobserver.onChanged(listOfnote) }
    }

    @Test
    fun `when getnotes request is made on db, it returns empty list`(){
        val emptylist = emptyList<Note>()
        every { repository.getAllNote() } returns Single.just(emptylist)
        viewModel.getNotes()
        verify { notelivedataobserver.onChanged(emptylist) }
        verify(exactly = 0) { errorobserver.onChanged("error") }
    }
}